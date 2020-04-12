package com.example.zoan.http.borrower

import com.example.zoan.ZoanException

class BorrowerNotFoundException(id: Long, message: String) : ZoanException(message) {

    var id: Long? = null

    constructor(id: Long) : this(id, "Borrower not found: (id: $id)")

}
