package com.example.zoan.http.payment

import com.example.zoan.domain.payment.PaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payments")
class PaymentController {

    @Autowired
    lateinit var repository: PaymentRepository

    @GetMapping
    fun list(): List<PaymentDto> {
        val payments = repository.findAll()
        return payments.map { payment -> PaymentDto(payment) }
    }
}
