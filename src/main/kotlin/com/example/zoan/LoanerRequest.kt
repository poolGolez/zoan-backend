package com.example.zoan

import org.springframework.stereotype.Component

@Component
class LoanerFactory {
    fun createFromRequest(request: CreateLoanerRequest): Loaner {
        val loaner = Loaner(request.name, 0.0)
        return loaner
    }
}

data class CreateLoanerRequest(
        val name: String
)
