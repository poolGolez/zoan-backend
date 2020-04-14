package com.example.zoan.domain.loan

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentScheduleRepository : CrudRepository<PaymentSchedule, Long> {
    fun findByLoan(loan: Loan): List<PaymentSchedule>
}
