package com.example.zoan.domain.loan

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository : CrudRepository<Loan, Long>
