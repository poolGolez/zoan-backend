package com.example.zoan.domain.equity

import com.example.zoan.domain.loaner.LoanerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(propagation = Propagation.MANDATORY)
class TransactionEnforcer {

    @Autowired
    lateinit var loanerRepository: LoanerRepository

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    fun applyTransaction(transaction: Transaction): Transaction {
        val loaner = transaction.loaner
        if (transaction.isCredit) {
            loaner.credit(transaction.amount)
        } else {
            loaner.debit(transaction.amount)
        }
        loanerRepository.save(loaner)
        transaction.apply()
        transactionRepository.save(transaction)

        return transaction
    }
}
