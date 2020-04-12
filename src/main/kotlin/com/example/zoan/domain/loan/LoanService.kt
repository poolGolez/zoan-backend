package com.example.zoan.domain.loan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoanService {

    @Autowired
    lateinit var loanFactory: LoanFactory

    @Autowired
    lateinit var loanRepository: LoanRepository

    fun createLoan(params: CreateLoanParams): Loan {
        val loan = loanFactory.createLoan(params)
        loanRepository.save(loan)
        return loan
    }
}
