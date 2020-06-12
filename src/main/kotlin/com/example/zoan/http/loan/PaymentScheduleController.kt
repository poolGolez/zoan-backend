package com.example.zoan.http.loan

import com.example.zoan.domain.loan.LoanRepository
import com.example.zoan.domain.loan.PaymentScheduleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class PaymentScheduleController {

    @Autowired
    lateinit var loanRepository: LoanRepository

    @Autowired
    lateinit var paymentSchedRepository: PaymentScheduleRepository

    @GetMapping("/api/loans/{loanId}/payment-schedules")
    fun listByLoan(@PathVariable loanId: Long): List<PaymentScheduleDto> {
        val loan = loanRepository.findByIdOrNull(loanId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val paymentSchedules = paymentSchedRepository.findByLoan(loan)
        return paymentSchedules.map { paymentSchedule ->
            PaymentScheduleDto(paymentSchedule)
        }
    }

    @GetMapping("/api/loans/{loanId}/payment-schedules/{scheduleId}")
    fun show(@PathVariable loanId: Long, @PathVariable scheduleId: Long): PaymentScheduleDto {
        val loan = loanRepository.findByIdOrNull(loanId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val paymentSchedule = paymentSchedRepository.findByIdAndLoan(scheduleId, loan)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return PaymentScheduleDto(paymentSchedule)
    }

    @GetMapping("/api/payment-schedules")
    fun query(requestParams: PaymentScheduleRequestQuery): List<PaymentScheduleDtoWithLoanContext> {
        val queryParams = requestParams.toQueryParams()
        val paymentSchedules =
                paymentSchedRepository.findByDateDueBetween(queryParams.dateFrom, queryParams.dateTo)

        return paymentSchedules.map { paymentSchedule ->
            PaymentScheduleDtoWithLoanContext(paymentSchedule)
        }
    }
}
