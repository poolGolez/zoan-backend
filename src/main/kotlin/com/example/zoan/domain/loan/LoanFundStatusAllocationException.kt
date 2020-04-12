package com.example.zoan.domain.loan

import com.example.zoan.ZoanException
import com.example.zoan.domain.fund.Fund

class LoanFundStatusAllocationException(val fund: Fund, message: String, cause: Exception?) : ZoanException(message, cause) {
    constructor(fund: Fund) : this(fund,
            "Fund status is not fo allocation (status: ${fund.status})",
            null)
}
