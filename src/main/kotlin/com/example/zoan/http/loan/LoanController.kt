package com.example.zoan.http.loan

import com.example.zoan.domain.loan.LoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/loans")
class LoanController {

    @Autowired
    lateinit var repository: LoanRepository

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

}
