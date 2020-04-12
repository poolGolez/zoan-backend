package com.example.zoan.domain.loan

import com.example.zoan.ZoanException
import com.example.zoan.domain.fund.Fund

class LoanFundAmountAllocationException(
        val fund: Fund,
        val loanAmount: Double,
        message: String,
        cause: Exception?
) : ZoanException(message, cause) {

    constructor(fund: Fund, loanAmount: Double) : this(
            fund,
            loanAmount,
            getDefaultMessage(loanAmount, fund.amount),
            null
    )

    companion object {
        fun getDefaultMessage(loanAmount: Double, fundAmount: Double): String {
            return "Fund amount must be equal to loaned amount" +
                    "(loan: %.2f, fund %.2f)".format(loanAmount, fundAmount)
        }
    }
}
