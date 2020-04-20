package com.example.zoan.domain.payment

import com.example.zoan.domain.borrower.Borrower
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "payments")
class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payments_id_seq")
    @SequenceGenerator(name = "payments_id_seq", sequenceName = "payments_id_seq")
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "payer_id")
    lateinit var payer: Borrower

    var amount: Double = 0.00

    lateinit var transactionDate: LocalDate

    var dateCreated = LocalDate.now()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payment")
    val allotments = mutableListOf<PaymentAllotment>()
}
