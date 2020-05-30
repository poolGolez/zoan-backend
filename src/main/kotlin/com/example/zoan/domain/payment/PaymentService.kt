package com.example.zoan.domain.payment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class PaymentService {

    @Autowired
    lateinit var paymentFactory: PaymentFactory

    @Autowired
    lateinit var paymentAllocator: PaymentAllocator

    @Autowired
    lateinit var paymentRepository: PaymentRepository

    fun createPayment(params: CreatePaymentParams): Payment {
        val payment = paymentFactory.createPayment(params)
        paymentRepository.save(payment)
        paymentAllocator.allocatePayment(payment)

        return payment
    }
}

data class CreatePaymentParams(
        val payerId: Long,
        val loanId: Long,
        val amount: Double,
        val transactionDate: LocalDate
)
