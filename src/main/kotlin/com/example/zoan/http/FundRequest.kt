package com.example.zoan.http

import com.example.zoan.CreateFundParams

data class CreateFundRequest(val owners: List<CreateFundRequestOwnerFragment>) {

    fun toCommand(): CreateFundParams {
        return CreateFundParams(this.owners)
    }

    data class CreateFundRequestOwnerFragment(
            val loanerId: Long,
            val amount: Double
    )
}
