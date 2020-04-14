package com.example.zoan.domain.loaner

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanerRepository : CrudRepository<Loaner, Long>
