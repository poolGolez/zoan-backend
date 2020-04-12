package com.example.zoan.http.borrower

import com.example.zoan.domain.borrower.Borrower

data class CreateBorrowerRequest(val name: String) {
    fun toBorrower(): Borrower {
        return Borrower(this.name)
    }
}
