package com.example.zoan.domain.payment

import com.example.zoan.domain.loan.Loan
import com.example.zoan.domain.loan.PaymentSchedule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PaymentAllocator {

    @Autowired
    lateinit var repository: PaymentAllotmentRepository

    @Throws(LoanAllocationCompleteException::class)
    fun allocatePayment(payment: Payment, loan: Loan) {
        if (loan.isComplete) {
            throw LoanAllocationCompleteException(loan)
        }
        // if amount is greater than outstanding; allow?

        var paymentAmount = payment.amount
        var paymentSchedule = nextSchedule(loan)
        while (paymentAmount > 0 && paymentSchedule !== null) {
            paymentAmount = allocateToSchedule(payment, paymentSchedule, paymentAmount)
            paymentSchedule = nextSchedule(loan)
        }

        if (loan.outstandingBalance == 0.0) {
            loan.status = Loan.LoanStatus.COMPLETE
        }
    }

    private fun nextSchedule(loan: Loan): PaymentSchedule? {
        return loan.pendingSchedules.sortedBy { it.dateDue }.firstOrNull()
    }

    private fun allocateToSchedule(payment: Payment, paymentSchedule: PaymentSchedule, paymentAmount: Double): Double {
        val excessAmount = paymentSchedule.cover(paymentAmount)

        val allotment = PaymentAllotment()
        allotment.payment = payment
        allotment.schedule = paymentSchedule
        allotment.amount = paymentAmount - excessAmount
        repository.save(allotment)

        payment.allotments.add(allotment)
        return excessAmount
    }

}
