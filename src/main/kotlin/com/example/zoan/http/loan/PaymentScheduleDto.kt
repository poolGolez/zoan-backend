package com.example.zoan.http.loan

import com.example.zoan.domain.loan.PaymentSchedule
import com.example.zoan.http.util.DateFormatter

class PaymentScheduleDto(paymentSchedule: PaymentSchedule) {
    val id = paymentSchedule.id
    val amountOwed = paymentSchedule.amountOwed
    val amountCovered = paymentSchedule.amountCovered
    val dateDue = DateFormatter.format(paymentSchedule.dateDue)
    val status = paymentSchedule.status
}
