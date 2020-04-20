package com.example.zoan.http.payment

import com.example.zoan.domain.payment.PaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

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

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): PaymentDto {
        val payment = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return PaymentDto(payment)
    }
}
