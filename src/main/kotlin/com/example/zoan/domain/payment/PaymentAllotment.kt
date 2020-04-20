package com.example.zoan.domain.payment

import com.example.zoan.domain.loan.PaymentSchedule
import javax.persistence.*

@Entity
@Table(name = "payment_allotments")
class PaymentAllotment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_allotments_id_seq")
    @SequenceGenerator(name = "payment_allotments_id_seq", sequenceName = "payment_allotments_id_seq")
    var id: Long? = null

    @ManyToOne
    lateinit var payment: Payment

    @ManyToOne
    lateinit var schedule: PaymentSchedule

    var amount: Double = 0.00
}
