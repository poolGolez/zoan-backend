package com.example.zoan.domain.loan

import com.example.zoan.ZoanException

class NoFundAllocatedException(
        val loan: Loan,
        message: String
) : ZoanException(message) {
    constructor(loan: Loan) : this(loan, getDefaultMessage(loan.id))

    companion object {
        private fun getDefaultMessage(loanId: Long?): String {
            return "No fund has been allocated to loan (id: %s)".format(loanId)
        }
    }
}
