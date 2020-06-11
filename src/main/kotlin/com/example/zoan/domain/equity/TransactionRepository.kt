package com.example.zoan.domain.equity

import com.example.zoan.domain.loaner.Loaner
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<Transaction, Long> {
    fun findByLoaner(loaner: Loaner): List<Transaction>
}
