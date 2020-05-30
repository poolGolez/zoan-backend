package com.example.zoan.http.payment

data class CreatePaymentRequest(
        val payerId: Long,
        val loanId: Long,
        val amount: Double
)
