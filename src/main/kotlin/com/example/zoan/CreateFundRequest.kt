package com.example.zoan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import javax.validation.constraints.NotNull
import kotlin.collections.MutableList

@Component
class FundFactory {

    @Autowired
    lateinit var loanerRepository: LoanerRepository

    fun createFromRequest(request: CreateFundRequest): Fund {
        val fund = Fund()
        fund.amount = request.totalAmount
        request.owners.forEach { fundOwner ->
            val loaner = loanerRepository.findByIdOrNull(fundOwner.loanerId)
                    ?: throw IllegalArgumentException("Unfound loaner {$request.loanerId}")
            fund.contribute(loaner, fundOwner.amount)
        }

        return fund
    }
}

data class CreateFundRequest(
        val totalAmount: Double,
        val owners: List<CreateFundRequestOwnerFragment>
) {

    data class CreateFundRequestOwnerFragment(
            val loanerId: Long,
            val amount: Double
    )
}