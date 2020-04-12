package com.example.zoan.domain.loan

import com.example.zoan.domain.borrower.BorrowerRepository
import com.example.zoan.domain.fund.FundRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class LoanFactory {

    @Autowired
    lateinit var borrowerRepository: BorrowerRepository

    @Autowired
    lateinit var fundRepository: FundRepository

    fun createLoan(params: CreateLoanParams): Loan {
        val borrower = borrowerRepository.findByIdOrNull(params.borrowerId)
                ?: throw Exception("Unknown borrower")
        val loan = Loan(params.amount, borrower)

        loan.monthlyInterest = params.monthlyInterest
        loan.installmentCount = params.installmentCount

        params.fundId?.let { fundId ->
            val fund = fundRepository.findByIdOrNull(fundId)
                    ?: throw Exception("Unknown funds")
            loan.allocate(fund)
        }

        return loan
    }

}

data class CreateLoanParams(
        var amount: Double,
        var borrowerId: Long,
        var monthlyInterest: Double,
        var installmentCount: Int,
        var fundId: Long?
)

