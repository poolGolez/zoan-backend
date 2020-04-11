package com.example.zoan

class InsufficientLoanerBalanceException(
        val loaner: Loaner,
        val amount: Double,
        message: String = "Insufficent balance for loaner"
) : ZoanException(message)
