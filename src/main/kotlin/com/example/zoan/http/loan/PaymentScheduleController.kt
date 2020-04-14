package com.example.zoan.http.loan

import com.example.zoan.domain.loan.LoanRepository
import com.example.zoan.domain.loan.PaymentScheduleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/loans/{loanId}")
class PaymentScheduleController {

    @Autowired
    lateinit var loanRepository: LoanRepository

    @Autowired
    lateinit var paymentSchedRepository: PaymentScheduleRepository

    @GetMapping("/payment-schedules")
    fun list(@PathVariable loanId: Long): List<PaymentScheduleDto> {
        val loan = loanRepository.findByIdOrNull(loanId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val paymentSchedules = paymentSchedRepository.findByLoan(loan)
        return paymentSchedules.map { paymentSchedule ->
            PaymentScheduleDto(paymentSchedule)
        }
    }
}
