package com.example.zoan

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "funds")
class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funds_id_seq")
    @SequenceGenerator(name = "funds_id_seq", sequenceName = "funds_id_seq")
    var id: Long? = null
        protected set

    var amount: Double = 0.00

    var dateCreated: Date = Date()
        protected set

    @Enumerated(EnumType.STRING)
    var status: FundStatus = FundStatus.FREE
        protected set

    @OneToMany(mappedBy = "fund", cascade = [CascadeType.ALL])
    var owners: MutableList<FundOwner> = mutableListOf<FundOwner>()

    fun contribute(loaner: Loaner, amount: Double): FundOwner {
        // TODO check for similar fund owners
        if (!loaner.canLend(amount)) {
            // TODO custom exception
            throw Exception("Loaner cannot lend much money")
        }

        val fundOwner = FundOwner(this, loaner, amount)
        this.owners.add(fundOwner)

        loaner.lend(amount)

        return fundOwner
    }

    fun allocate() {
        // TODO: check if status is FREE
        this.status = FundStatus.ALLOCATED
    }
}

enum class FundStatus {
    FREE, ALLOCATED, CLOSED
}
