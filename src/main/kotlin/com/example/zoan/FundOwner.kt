package com.example.zoan

import javax.persistence.*


@Entity
@Table(name = "fund_owners")
class FundOwner() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fund_owners_id_seq")
    @SequenceGenerator(name = "fund_owners_id_seq", sequenceName = "fund_owners_id_seq")
    var id: Long? = null
        protected set

    @ManyToOne
    @JoinColumn(name = "fund_id", nullable = false)
    lateinit var fund: Fund

    @ManyToOne
    @JoinColumn(name = "loaner_id", nullable = false)
    lateinit var loaner: Loaner

    var amountAllocated: Double = 0.00

    var amountReimbursed: Double = 0.00
        protected set

    constructor(fund: Fund, loaner: Loaner, amount: Double) : this() {
        this.fund = fund
        this.loaner = loaner
        this.amountAllocated = amount
    }

    val netLoanedAmount get() = this.amountAllocated - this.amountReimbursed
}
