package com.example.zoan

import java.util.*

class FundDto(fund: Fund) {

    var id: Long? = fund.id
        private set

    var totalAmount: Double? = fund.amount
        private set

    var dateCreated: Date? = fund.dateCreated
        private set

    var status: FundStatus? = fund.status
        private set

    private val _owners: List<FundOwner> = fund.owners
    val owners: List<FundOwnerDto>
        get() {
            return this._owners.map { fundOwner -> FundOwnerDto(fundOwner) }
        }

    inner class FundOwnerDto(fundOwner: FundOwner) {
        val id = fundOwner.id

        private val _loaner = fundOwner.loaner

        val amount = fundOwner.amountAllocated

        val loaner: Map<String, Any?>
            get() {
                return mapOf(
                        "id" to this._loaner.id,
                        "name" to this._loaner.name,
                        "status" to this._loaner.status
                )
            }
    }

}
