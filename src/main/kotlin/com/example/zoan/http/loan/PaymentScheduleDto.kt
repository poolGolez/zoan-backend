package com.example.zoan.http.loan

import com.example.zoan.domain.loan.PaymentSchedule

class PaymentScheduleDto(paymentSchedule: PaymentSchedule) {
    val id = paymentSchedule.id
    val amountOwed = paymentSchedule.amountOwed
    val amountCovered = paymentSchedule.amountCovered
    val dateDue = paymentSchedule.dateDue
    val status = paymentSchedule.status
}
