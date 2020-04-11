package com.example.zoan.domain.fund

import com.example.zoan.domain.loaner.LoanerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class FundFactory {

    @Autowired
    lateinit var loanerRepository: LoanerRepository

    fun buildFundFromParams(params: CreateFundParams): Fund {
        val fund = Fund()
        params.owners.forEach { fundOwner ->
            val loaner = loanerRepository.findByIdOrNull(fundOwner.loanerId)
                    ?: throw IllegalArgumentException("Unfound loaner {$fundOwner.loanerId}")
            fund.contribute(loaner, fundOwner.amount)
            fund.amount += fundOwner.amount
        }

        return fund
    }
}
