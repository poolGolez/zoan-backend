package com.example.zoan.domain.payment

import com.example.zoan.domain.borrower.BorrowerRepository
import com.example.zoan.domain.loan.LoanNotFoundException
import com.example.zoan.domain.loan.LoanRepository
import com.example.zoan.http.borrower.BorrowerNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PaymentFactory {

    @Autowired
    lateinit var loanRepository: LoanRepository

    @Autowired
    lateinit var borrowerRepository: BorrowerRepository

    fun createPayment(params: CreatePaymentParams): Payment {
        val payer = borrowerRepository.findByIdOrNull(params.payerId)
                ?: throw BorrowerNotFoundException(params.payerId)

        val loan = loanRepository.findByIdOrNull(params.loanId)
                ?: throw LoanNotFoundException(params.loanId)

        val payment = Payment()
        payment.payer = payer
        payment.loan = loan
        payment.amount = params.amount
        payment.transactionDate = params.transactionDate
        return payment
    }

}
