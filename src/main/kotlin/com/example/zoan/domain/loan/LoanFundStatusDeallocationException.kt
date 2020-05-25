package com.example.zoan.domain.loan

import com.example.zoan.ZoanException

class LoanFundStatusDeallocationException(
        val loan: Loan,
        message: String,
        cause: Exception?
) : ZoanException(message, cause) {

    constructor(loan: Loan) : this(loan,
            getDefaultMessage(loan), null)

    companion object {

        private fun getDefaultMessage(loan: Loan): String {
            return "Cannot deallocate loan when status is {status: %s}".format(loan.status)

        }
    }
}
