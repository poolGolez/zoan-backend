package com.example.zoan.domain.loan

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "payment_schedules")
class PaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_schedules_id_seq")
    @SequenceGenerator(name = "payment_schedules_id_seq", sequenceName = "payment_schedules_id_seq")
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    lateinit var loan: Loan

    var amountOwed: Double = 0.00

    var amountCovered: Double = 0.00

    lateinit var dateDue: LocalDate

    @Enumerated(EnumType.STRING)
    var status = PaymentScheduleStatus.PENDING
        protected set

    fun cover(amount: Double): Double {
        if (amount >= amountPayable()) {
            val excess = amount - amountPayable()
            amountCovered += amountPayable()
            status = PaymentScheduleStatus.COVERED
            return excess
        } else {
            amountCovered += amount
            return 0.0
        }
    }

    fun amountPayable(): Double {
        return amountOwed - amountCovered
    }

    override fun toString(): String {
        return "${id}: ${amountOwed}    ${amountCovered} ${status} ${dateDue}"
    }

    enum class PaymentScheduleStatus {
        PENDING, COVERED
    }
}
