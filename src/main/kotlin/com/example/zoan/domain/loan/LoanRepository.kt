package com.example.zoan.domain.loan

import com.example.zoan.domain.borrower.Borrower
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository : CrudRepository<Loan, Long> {

//    fun findByIdAndBorrowerOrNull(loanId: Long, borrower: Borrower): Loan?
}
