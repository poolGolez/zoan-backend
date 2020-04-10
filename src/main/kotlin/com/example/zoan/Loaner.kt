package com.example.zoan

import javax.persistence.*

@Entity
@Table(name = "loaners")
class Loaner() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loaners_id_seq")
    @SequenceGenerator(name = "loaners_id_seq", sequenceName = "loaners_id_seq")
    var id: Long? = null
        protected set

    var name: String? = null

    var amountFree: Double = 0.00
        protected set

    var amountLoaned: Double = 0.00
        protected set

    var status: LoanerStatus = LoanerStatus.ACTIVE
        protected set

    constructor(capital: Double) : this() {
        this.amountFree = capital
        this.amountLoaned = 0.00
    }

    constructor(name: String, capital: Double) : this(capital) {
        this.name = name
    }

    fun lend(amount: Double) {
        this.amountLoaned += amount
        this.amountFree -= amount
    }

    fun reimburse(amount: Double) {
        this.amountLoaned -= amount
        this.amountFree += amount
    }

    fun deactivate() {
        if (this.status == LoanerStatus.ACTIVE) {
            this.status = LoanerStatus.INACTIVE
        }
    }

    fun activate() {
        if (this.status == LoanerStatus.INACTIVE) {
            this.status = LoanerStatus.ACTIVE
        }
    }
}
