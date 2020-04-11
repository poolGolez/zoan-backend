package com.example.zoan.domain.borrower

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BorrowerRepository : CrudRepository<Borrower, Long>
