package com.example.zoan.domain.loan

import com.example.zoan.ZoanException
import com.example.zoan.domain.borrower.Borrower
import com.example.zoan.domain.fund.Fund
import com.example.zoan.domain.fund.FundStatus
import java.util.*
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

    var dateCreated: Date = Date()
        protected set

    @Enumerated(EnumType.STRING)
    var status: LoanStatus = LoanStatus.DRAFT

    @OneToMany(mappedBy = "loan")
    val schedules = mutableListOf<PaymentSchedule>()

    val pendingSchedules: List<PaymentSchedule>
        get() = schedules.filter { it.status == PaymentSchedule.PaymentScheduleStatus.PENDING }

    constructor(amount: Double, borrower: Borrower) : this() {
        this.amount = amount
        this.borrower = borrower
    }

    fun allocate(fund: Fund) {
        if (this.fund == fund) {
            return
        }

        if (fund.status != FundStatus.FREE) {
            throw LoanFundStatusAllocationException(fund)
        }

        if (fund.amount != this.amount) {
            throw LoanFundAmountAllocationException(fund, amount)
        }

        this.fund = fund
        fund.allocate()
    }

    @Throws(ZoanException::class)
    fun deallocate(): Fund {
        if (status != LoanStatus.DRAFT) {
            throw LoanFundStatusDeallocationException(this)
        }

        if (fund == null) {
            throw NoFundAllocatedException(this)
        }

        val deallocatedFund = fund!!
        this.fund = null
        deallocatedFund.deallocate()
        return deallocatedFund
    }

    fun activate() {
        if (status != LoanStatus.DRAFT) {
            throw LoanStatusActivationException(this)
        }
        if (fund == null) {
            throw NoFundAllocatedException(this)
        }

        status = LoanStatus.ACTIVE
    }

    fun computeInstallmentPayment(): Double {
        return amount * (1 + monthlyInterest * (installmentCount / 2)) / installmentCount
    }


    enum class LoanStatus {
        DRAFT, ACTIVE, COMPLETE, CLOSED
    }

}
