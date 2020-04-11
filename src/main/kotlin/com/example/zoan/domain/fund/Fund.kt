package com.example.zoan.domain.fund

import com.example.zoan.domain.loaner.Loaner
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
        if (!loaner.canLend(amount)) {
            throw InsufficientLoanerBalanceException(loaner, amount);
        }

        var fundOwner = this.owners.find { fundOwner ->
            fundOwner.loaner == loaner
        }

        if (fundOwner == null) {
            fundOwner = FundOwner(this, loaner, amount)
            this.owners.add(fundOwner)
        } else {
            fundOwner.amountAllocated += amount
        }
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
