package com.example.zoan.http.payment

import com.example.zoan.domain.payment.CreatePaymentParams
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import java.util.*

data class CreatePaymentRequest(
        val payerId: Long,
        val loanId: Long,
        val amount: Double,
        val transactionDate: String
) {
    fun toCommand() : CreatePaymentParams {
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH)
        return CreatePaymentParams(
                payerId,
                loanId,
                amount,
                LocalDate.parse(transactionDate, formatter)
        )
    }
}
