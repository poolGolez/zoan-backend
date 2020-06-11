package com.example.zoan.http.equity

import com.example.zoan.domain.equity.Transaction

class TransactionDto(transaction: Transaction) {
    val id = transaction.id
    val amount = transaction.amount
    val transactionDate = transaction.transactionDate
    val type = transaction.type
    val notes = transaction.notes
    val status = transaction.status
}
