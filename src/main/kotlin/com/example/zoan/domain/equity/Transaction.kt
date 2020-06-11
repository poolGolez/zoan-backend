package com.example.zoan.domain.equity

import com.example.zoan.domain.loaner.Loaner
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "equity_transactions")
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equity_transactions_id_seq")
    @SequenceGenerator(name = "equity_transactions_id_seq", sequenceName = "equity_transactions_id_seq")
    var id: Long? = null
        protected set

    @ManyToOne
    lateinit var loaner: Loaner

    var amount: Double = 0.0

    lateinit var transactionDate: LocalDate

    val dateCreated = Date()

    var dateApplied: Date? = null

    var notes: String? = null

    @Enumerated(EnumType.STRING)
    lateinit var type: TransactionType

    @Enumerated(EnumType.STRING)
    var status: TransactionStatus = TransactionStatus.PENDING

    val isCredit get() = (type == TransactionType.CREDIT)

    val isDebit get() = (type == TransactionType.DEBIT)

    fun apply() {
        status = TransactionStatus.APPLIED
        dateApplied = Date()
    }
}

enum class TransactionStatus {
    PENDING, APPLIED
}

enum class TransactionType {
    DEBIT, CREDIT
}
