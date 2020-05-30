package com.example.zoan.domain.loan

import com.example.zoan.ZoanException

class LoanNotFoundException(
        val id: Long,
        message: String
) : ZoanException(message) {
    constructor(id: Long) : this(id, "Loan not found: (id: $id)")
}
