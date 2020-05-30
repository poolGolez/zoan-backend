package com.example.zoan.domain.payment

import com.example.zoan.domain.loan.Loan
import com.example.zoan.domain.loan.PaymentSchedule
import org.springframework.stereotype.Component

@Component
class PaymentAllocator {

    fun allocatePayment(payment: Payment, loan: Loan) {
        // check if loan is full; if amount is greater than outstanding

        var paymentAmount = payment.amount
        var paymentSchedule = nextSchedule(loan)
        while (paymentAmount > 0 && paymentSchedule !== null) {
            paymentAmount = paymentSchedule.cover(paymentAmount)
            paymentSchedule = nextSchedule(loan)
        }

        //if all schedules are covered, complete the loan
    }

    private fun nextSchedule(loan: Loan): PaymentSchedule? {
        return loan.pendingSchedules.sortedBy { it.dateDue }.firstOrNull()
    }

}
