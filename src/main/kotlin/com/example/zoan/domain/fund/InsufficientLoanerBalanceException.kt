package com.example.zoan.domain.fund

import com.example.zoan.domain.loaner.Loaner
import com.example.zoan.ZoanException

class InsufficientLoanerBalanceException(
        val loaner: Loaner,
        val amount: Double,
        message: String = "Insufficent balance for loaner"
) : ZoanException(message)
