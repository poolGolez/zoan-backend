package com.example.zoan.domain.payment

import com.example.zoan.ZoanException
import com.example.zoan.domain.loan.Loan

class LoanAllocationCompleteException(
        val loan: Loan,
        message: String
) : ZoanException(message) {

    constructor(loan: Loan) : this(loan, getDefaultMessage(loan.id))

    companion object {
        fun getDefaultMessage(id: Long?): String {
            return ("Loan to allocate payment for is already COMPLETE (id: %s)").format(id)
        }
    }
}
