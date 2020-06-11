package com.example.zoan.domain.equity

import com.example.zoan.domain.loaner.Loaner
import org.springframework.stereotype.Component

@Component
class TransactionFactory {
    fun buildTransaction(loaner: Loaner, params: CreateTransactionParams): Transaction {
        val transaction = Transaction()
        transaction.loaner = loaner
        transaction.amount = params.amount
        transaction.transactionDate = params.transactionDate
        transaction.type = params.transactionType
        transaction.notes = params.notes

        return transaction
    }
}
