package com.example.zoan.domain.loan

import com.example.zoan.ZoanException
import com.example.zoan.domain.fund.Fund
import com.example.zoan.domain.fund.FundStatus

class LoanFundStatusAllocationException(
        val fund: Fund,
        message: String,
        cause: Exception?
) : ZoanException(message, cause) {

    constructor(fund: Fund) : this(fund,
            getDefaultMessage(fund.status),
            null)

    companion object {
        private fun getDefaultMessage(status: FundStatus): String {
            return "Fund status is not fo allocation (status: %s)"
                    .format(status)
        }
    }
}
