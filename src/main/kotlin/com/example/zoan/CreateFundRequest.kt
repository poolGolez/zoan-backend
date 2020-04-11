package com.example.zoan

data class CreateFundRequest(val owners: List<CreateFundRequestOwnerFragment>) {

    fun toCommand(): CreateFundParams {
        return CreateFundParams(this.owners)
    }

    data class CreateFundRequestOwnerFragment(
            val loanerId: Long,
            val amount: Double
    )
}
