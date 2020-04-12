package com.example.zoan.domain.borrower

import javax.persistence.*

@Entity
@Table(name = "borrowers")
class Borrower() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowers_id_seq")
    @SequenceGenerator(name = "borrowers_id_seq", sequenceName = "borrowers_id_seq")
    var id: Long? = null

    var name: String? = null

    @Enumerated(EnumType.STRING)
    var status: BorrowerStatus = BorrowerStatus.ACTIVE

    enum class BorrowerStatus {
        ACTIVE, INACTIVE
    }

    constructor(name: String) : this() {
        this.name = name
    }
}
