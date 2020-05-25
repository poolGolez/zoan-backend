package com.example.zoan.http.loan

import com.example.zoan.domain.loan.CreateLoanParams
import com.example.zoan.domain.loan.UpdateLoanParams

data class CreateLoanRequest(
        val amount: Double,
        val borrowerId: Long,
        val monthlyInterest: Double,
        val installmentCount: Int,
        val fundId: Long?
) {
    fun toCommand(): CreateLoanParams {
        return CreateLoanParams(
                amount,
                borrowerId,
                monthlyInterest,
                installmentCount,
                fundId
        )
    }
}

data class UpdateLoanRequest(
        val amount: Double,
        val borrowerId: Long,
        val monthlyInterest: Double,
        val installmentCount: Int,
        val fundId: Long?
) {
    fun toCommand(): UpdateLoanParams {
        return UpdateLoanParams(
                amount,
                borrowerId,
                monthlyInterest,
                installmentCount,
                fundId
        )
    }

}
