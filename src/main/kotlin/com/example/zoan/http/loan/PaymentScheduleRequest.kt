package com.example.zoan.http.loan

import com.example.zoan.domain.loan.PaymentScheduleQueryParams
import com.example.zoan.http.util.DateFormatter
import java.time.YearMonth

data class PaymentScheduleRequestQuery(
        val from: String?,
        val to: String?
) {
    fun toQueryParams(): PaymentScheduleQueryParams {
        val dateFrom = from?.let { DateFormatter.parse(it) } ?: YearMonth.now().atDay(1)
        val dateTo = to?.let { DateFormatter.parse(it) } ?: YearMonth.now().atEndOfMonth()
        return PaymentScheduleQueryParams(
                dateFrom,
                dateTo
        )
    }
}
