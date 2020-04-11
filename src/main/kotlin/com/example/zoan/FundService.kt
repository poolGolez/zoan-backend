package com.example.zoan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FundService {

    @Autowired
    lateinit var fundRepository: FundRepository

    @Autowired
    lateinit var fundFactory: FundFactory

    @Autowired
    lateinit var loanerRepository: LoanerRepository

    fun createFund(params: CreateFundParams): Fund {
        val fund = fundFactory.buildFundFromParams(params)
        fundRepository.save(fund)
        return fund
    }
}

data class CreateFundParams(val owners: List<CreateFundRequest.CreateFundRequestOwnerFragment>)
