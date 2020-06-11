package com.example.zoan.domain.equity

import com.example.zoan.domain.loaner.Loaner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class TransactionService {

    @Autowired
    lateinit var repository: TransactionRepository

    @Autowired
    lateinit var factory: TransactionFactory

    @Autowired
    lateinit var transactionEnforcer: TransactionEnforcer

    fun createTransaction(loaner: Loaner, params: CreateTransactionParams): Transaction {
        val transaction = factory.buildTransaction(loaner, params)
        repository.save(transaction)

        return transactionEnforcer.applyTransaction(transaction)
    }
}

data class CreateTransactionParams(
        val transactionDate: LocalDate,
        val amount: Double,
        val transactionType: TransactionType,
        val notes: String?
)
