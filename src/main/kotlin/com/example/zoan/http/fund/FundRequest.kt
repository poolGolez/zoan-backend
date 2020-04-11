package com.example.zoan.http.fund

import com.example.zoan.domain.fund.CreateFundParams

data class CreateFundRequest(val owners: List<CreateFundRequestOwnerFragment>) {

    fun toCommand(): CreateFundParams {
        return CreateFundParams(this.owners.map { fundOwner ->
            CreateFundParams.OwnerFragment(fundOwner.loanerId, fundOwner.amount)
        })
    }

    data class CreateFundRequestOwnerFragment(
            val loanerId: Long,
            val amount: Double
    )
}
