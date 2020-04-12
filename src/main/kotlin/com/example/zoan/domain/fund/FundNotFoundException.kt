package com.example.zoan.domain.fund

import com.example.zoan.ZoanException

class FundNotFoundException(
        val id: Long,
        message: String
) : ZoanException(message) {
    constructor(id: Long) : this(id, "Fund not found: (id: $id)")
}
