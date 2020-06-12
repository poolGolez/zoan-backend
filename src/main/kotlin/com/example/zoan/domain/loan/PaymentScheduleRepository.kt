package com.example.zoan.domain.loan

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface PaymentScheduleRepository : CrudRepository<PaymentSchedule, Long> {
    fun findByLoan(loan: Loan): List<PaymentSchedule>
    fun findByIdAndLoan(id: Long, loan: Loan): PaymentSchedule?
    fun findByDateDueBetween(from: LocalDate, to: LocalDate): List<PaymentSchedule>
}

data class PaymentScheduleQueryParams(
        val dateFrom: LocalDate,
        val dateTo: LocalDate
)
