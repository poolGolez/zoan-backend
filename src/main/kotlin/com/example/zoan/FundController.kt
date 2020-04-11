package com.example.zoan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/funds")
class FundController {

    @Autowired
    lateinit var repository: FundRepository

    @GetMapping
    fun list(): List<FundDTO> {
        val funds = repository.findAll()
        return funds.map { FundDTO(it) }
    }
}

class FundDTO(fund: Fund) {

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

}

class FundOwnerDto(fundOwner: FundOwner) {
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
