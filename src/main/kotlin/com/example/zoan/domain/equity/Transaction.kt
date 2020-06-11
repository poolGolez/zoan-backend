package com.example.zoan.domain.equity

import com.example.zoan.domain.loaner.Loaner
import java.time.LocalDate
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

    val dateCreated = LocalDate.now()

    var notes: String? = null

    @Enumerated(EnumType.STRING)
    lateinit var type: TransactionType

}

enum class TransactionType {
    DEBIT, CREDIT
}
