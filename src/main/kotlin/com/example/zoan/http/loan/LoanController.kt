package com.example.zoan.http.loan

import com.example.zoan.domain.loan.LoanRepository
import com.example.zoan.domain.loan.LoanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/loans")
class LoanController {

    @Autowired
    lateinit var repository: LoanRepository

    @Autowired
    lateinit var service: LoanService

    @GetMapping
    fun list(): List<LoanDto> {
        return repository.findAll().map { loan -> LoanDto(loan) }
    }

    @GetMapping("{id}")
    fun show(@PathVariable id: Long): LoanDto {
        val loan = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return LoanDto(loan)
    }

    @PostMapping
    fun create(@RequestBody request: CreateLoanRequest): LoanDto {
        val params = request.toCommand()
        val loan = service.createLoan(params)
        return LoanDto(loan)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: UpdateLoanRequest): LoanDto {
        val loan = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val params = request.toCommand()
        service.updateLoan(loan, params)
        return LoanDto(loan)
    }

    @PostMapping("/{id}/activate")
    fun activate(@PathVariable id: Long): LoanDto {
        val loan = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        service.activateLoan(loan)
        return LoanDto(loan)
    }

}
