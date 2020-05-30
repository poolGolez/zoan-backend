package com.example.zoan.domain.loan

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PaymentScheduleFactory {

    fun createPaymentSchedules(loan: Loan): Iterable<PaymentSchedule> {
        val iterator = PaymentScheduleIterator(loan)
        val paymentSchedules = iterator.asSequence().map { dueDate: LocalDate ->
            val paymentSchedule = PaymentSchedule()
            paymentSchedule.loan = loan
            paymentSchedule.dateDue = dueDate
            paymentSchedule.amountOwed = loan.computeInstallmentPayment()
            paymentSchedule
        }
        return paymentSchedules.toList()
    }
}

class PaymentScheduleIterator(loan: Loan) : Iterator<LocalDate> {

    private val numberOfInstallments: Int = loan.installmentCount

    private lateinit var dueDate: LocalDate

    private var installmentCount: Int = 1

    init {
        val tomorrow = LocalDate.now().plusDays(1)
        dueDate = if (tomorrow.dayOfMonth <= 15)
            (LocalDate.of(tomorrow.year, tomorrow.month, 15)) else
            (LocalDate.of(tomorrow.year, tomorrow.month, tomorrow.lengthOfMonth()))
    }

    override fun hasNext(): Boolean = installmentCount <= numberOfInstallments

    override fun next(): LocalDate {
        val result: LocalDate = dueDate

        if (dueDate.dayOfMonth <= 15) {
            dueDate = LocalDate.of(dueDate.year, dueDate.month, dueDate.lengthOfMonth())
        } else {
            dueDate = dueDate.plusDays(15)
        }
        installmentCount++

        return result
    }
}
