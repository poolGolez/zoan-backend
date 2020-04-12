package com.example.zoan.http.loan

import com.example.zoan.domain.loan.LoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/loans")
class LoanController {

    @Autowired
    lateinit var repository: LoanRepository

    @GetMapping
    fun list(): List<LoanDto> {
        return repository.findAll().map { loan -> LoanDto(loan) }
    }

}
