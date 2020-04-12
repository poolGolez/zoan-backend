package com.example.zoan.http.borrower

import com.example.zoan.ZoanException

class BorrowerNotFoundException(
        val id: Long,
        message: String
) : ZoanException(message) {
    constructor(id: Long) : this(id, "Borrower not found: (id: $id)")
}
