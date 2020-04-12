package com.example.zoan.domain.loan

import com.example.zoan.ZoanException

class LoanCreationException(
        message: String,
        cause: Exception?
) : ZoanException(message, cause) {
    constructor(cause: Exception) : this("Unable to create loan", cause)
}
