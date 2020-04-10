package com.example.zoan

import org.springframework.stereotype.Component

@Component
class LoanerFactory {
    fun createFromRequest(request: CreateLoanerRequest): Loaner {
        val loaner = Loaner(request.name, 0.0)
        return loaner
    }

    fun updateFromRequest(loaner: Loaner, request: UpdateLoanerRequest): Loaner {
        if(request.name != null) loaner.name = request.name
        return loaner
    }
}

data class CreateLoanerRequest(
        val name: String
)

data class UpdateLoanerRequest(
        val name: String?
)
