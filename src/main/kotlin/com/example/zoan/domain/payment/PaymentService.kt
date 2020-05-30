package com.example.zoan.domain.payment

import com.example.zoan.domain.loan.LoanNotFoundException
import com.example.zoan.domain.loan.LoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
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

    @Autowired
    lateinit var loanRepository: LoanRepository

    fun createPayment(params: CreatePaymentParams): Payment {
        val payment = paymentFactory.createPayment(params)

        val loan = loanRepository.findByIdOrNull(params.loanId)
                ?: throw LoanNotFoundException(params.loanId)
        paymentAllocator.allocatePayment(payment, loan)

        paymentRepository.save(payment)
        return payment
    }
}

data class CreatePaymentParams(
        val payerId: Long,
        val loanId: Long,
        val amount: Double,
        val transactionDate: LocalDate
)
