package com.example.zoan.domain.payment

import com.example.zoan.domain.loan.Loan
import com.example.zoan.domain.loan.PaymentSchedule
import org.springframework.stereotype.Component

@Component
class PaymentAllocator {

    @Throws(LoanAllocationCompleteException::class)
    fun allocatePayment(payment: Payment, loan: Loan) {
        if (loan.isComplete) {
            throw LoanAllocationCompleteException(loan)
        }
        // if amount is greater than outstanding; allow?

        var paymentAmount = payment.amount
        var paymentSchedule = nextSchedule(loan)
        while (paymentAmount > 0 && paymentSchedule !== null) {
            paymentAmount = paymentSchedule.cover(paymentAmount)
            paymentSchedule = nextSchedule(loan)
        }

        if (loan.outstandingBalance == 0.0) {
            loan.status = Loan.LoanStatus.COMPLETE
        }
    }

    private fun nextSchedule(loan: Loan): PaymentSchedule? {
        return loan.pendingSchedules.sortedBy { it.dateDue }.firstOrNull()
    }

}
