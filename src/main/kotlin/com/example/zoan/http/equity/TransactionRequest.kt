package com.example.zoan.http.equity

import com.example.zoan.domain.equity.CreateTransactionParams
import com.example.zoan.domain.equity.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class CreateTransactionRequest(
        val transactionDate: String,
        val amount: Double,
        val type: String,
        val notes: String?
) {
    fun toCommand(): CreateTransactionParams {
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH)
        return CreateTransactionParams(
                LocalDate.parse(transactionDate, formatter),
                amount,
                TransactionType.valueOf(type),
                notes

        )
    }
}
