package com.example.zoan.http.equity

import com.example.zoan.domain.equity.Transaction
import com.example.zoan.http.util.DateFormatter

class TransactionDto(transaction: Transaction) {
    val id = transaction.id
    val amount = transaction.amount
    val transactionDate = DateFormatter.format(transaction.transactionDate)
    val type = transaction.type
    val notes = transaction.notes
    val status = transaction.status
}
