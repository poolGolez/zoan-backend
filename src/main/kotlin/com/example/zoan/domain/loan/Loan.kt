package com.example.zoan.domain.loan

import com.example.zoan.domain.borrower.Borrower
import com.example.zoan.domain.fund.Fund
import com.example.zoan.domain.fund.FundStatus
import javax.persistence.*

@Entity
@Table(name = "loans")
class Loan() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loans_id_seq")
    @SequenceGenerator(name = "loans_id_seq", sequenceName = "loans_id_seq")
    var id: Long? = null

    var amount: Double = 0.00

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    lateinit var borrower: Borrower

    @OneToOne
    @JoinColumn(name = "fund_id", nullable = true)
    var fund: Fund? = null
        protected set

    var monthlyInterest: Double = 0.0

    var installmentCount: Int = 1

    @Enumerated(EnumType.STRING)
    var status: LoanStatus = LoanStatus.DRAFT

    constructor(amount: Double, borrower: Borrower) : this() {
        this.amount = amount
        this.borrower = borrower
    }

    fun allocate(fund: Fund) {
        if (fund.status != FundStatus.FREE) {
            throw LoanFundStatusAllocationException(fund)
        }

        if (fund.amount != this.amount) {
            throw LoanFundAmountAllocationException(fund, amount)
        }

        this.fund = fund
        fund.allocate()
    }

    fun activate() {
        if (status != LoanStatus.DRAFT) {
            throw Exception("Loan status is not eligible for activation")
        }
        if (fund == null) {
            throw Exception("No fund has been allocated to loan")
        }

        status = LoanStatus.ACTIVE
    }

    enum class LoanStatus {
        DRAFT, ACTIVE, COMPLETE, CLOSED
    }

}
