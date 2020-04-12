package com.example.zoan.http.loan

import com.example.zoan.domain.loan.CreateLoanParams

data class CreateLoanRequest(
        val amount: Double,
        val borrowerId: Long,
        val monthlyInterest: Double,
        val installmentCount: Int
) {
    fun toCommand(): CreateLoanParams {
        return CreateLoanParams(amount,
                borrowerId,
                monthlyInterest,
                installmentCount)
    }
}
