package com.example.zoan.domain.loan

import com.example.zoan.domain.borrower.BorrowerRepository
import com.example.zoan.domain.fund.FundNotFoundException
import com.example.zoan.domain.fund.FundRepository
import com.example.zoan.http.borrower.BorrowerNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class LoanFactory {

    @Autowired
    lateinit var borrowerRepository: BorrowerRepository

    @Autowired
    lateinit var fundRepository: FundRepository

    @Throws(
            BorrowerNotFoundException::class,
            FundNotFoundException::class
    )
    fun createLoan(params: CreateLoanParams): Loan {
        val borrower = borrowerRepository.findByIdOrNull(params.borrowerId)
                ?: throw BorrowerNotFoundException(params.borrowerId)
        val loan = Loan(params.amount, borrower)

        loan.monthlyInterest = params.monthlyInterest
        loan.installmentCount = params.installmentCount

        params.fundId?.let { fundId ->
            val fund = fundRepository.findByIdOrNull(fundId)
                    ?: throw FundNotFoundException(fundId)
            loan.allocate(fund)
        }

        return loan
    }

    @Throws(
            BorrowerNotFoundException::class,
            FundNotFoundException::class
    )
    fun updateLoan(loan: Loan, params: UpdateLoanParams): Loan {
        if(loan.status !== Loan.LoanStatus.DRAFT) {
            throw LoanStatusUpdateException(loan)
        }

        loan.amount = params.amount
        loan.borrower = borrowerRepository.findByIdOrNull(params.borrowerId)
                ?: throw BorrowerNotFoundException(params.borrowerId)
        loan.installmentCount = params.installmentCount
        loan.monthlyInterest = params.monthlyInterest

        if(params.fundId !== null) {
            val fund = fundRepository.findByIdOrNull(params.fundId!!)
                    ?: throw FundNotFoundException(params.fundId!!)
            loan.allocate(fund)
        } else if(loan.fund !== null){
            loan.deallocate()
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

data class UpdateLoanParams(
        var amount: Double,
        var borrowerId: Long,
        var monthlyInterest: Double,
        var installmentCount: Int,
        var fundId: Long?
)

