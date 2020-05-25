package com.example.zoan.domain.loan

import com.example.zoan.ZoanException

class LoanStatusUpdateException(
        val loan: Loan,
        message: String
) : ZoanException(message) {
    constructor(loan: Loan) : this(
            loan,
            getDefaultMessage(loan.status)
    )

    companion object {
        fun getDefaultMessage(status: Loan.LoanStatus): String {
            return ("Loan status is not eligible for " +
                    "update (status: %s), should be DRAFT").format(status)
        }
    }
}
