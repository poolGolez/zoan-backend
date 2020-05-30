package com.example.zoan.domain.payment

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentAllotmentRepository : CrudRepository<PaymentAllotment, Long>
