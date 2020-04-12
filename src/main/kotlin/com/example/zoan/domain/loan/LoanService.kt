package com.example.zoan.domain.loan

import com.example.zoan.http.borrower.BorrowerNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoanService {

    @Autowired
    lateinit var loanFactory: LoanFactory

    @Autowired
    lateinit var loanRepository: LoanRepository

    fun createLoan(params: CreateLoanParams): Loan {
        try{
            val loan = loanFactory.createLoan(params)
            loanRepository.save(loan)
            return loan
        } catch(exception: BorrowerNotFoundException) {
            throw LoanCreationException(exception)
        }

    }
}
