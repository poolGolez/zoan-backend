package com.example.zoan.domain.loan

import com.example.zoan.ZoanException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoanService {

    @Autowired
    lateinit var loanFactory: LoanFactory

    @Autowired
    lateinit var paymentSchedFactory: PaymentScheduleFactory

    @Autowired
    lateinit var loanRepository: LoanRepository

    @Autowired
    lateinit var paymentSchedRepo: PaymentScheduleRepository

    fun createLoan(params: CreateLoanParams): Loan {
        try {
            val loan = loanFactory.createLoan(params)
            loanRepository.save(loan)
            return loan
        } catch (exception: ZoanException) {
            throw LoanCreationException(exception)
        }
    }

    fun activateLoan(loan: Loan): Loan {
        loan.activate()
        val paymentSchedules = paymentSchedFactory.createPaymentSchedules(loan)

        loanRepository.save(loan)
        paymentSchedRepo.saveAll(paymentSchedules)
        return loan
    }
}
