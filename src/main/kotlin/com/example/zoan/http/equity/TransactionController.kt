package com.example.zoan.http.equity

import com.example.zoan.domain.equity.CreateTransactionParams
import com.example.zoan.domain.equity.Transaction
import com.example.zoan.domain.equity.TransactionService
import com.example.zoan.domain.loaner.LoanerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/loaners/{loanerId}/equity/transactions")
class TransactionController {

    @Autowired
    lateinit var loanerRepository: LoanerRepository

    @Autowired
    lateinit var service: TransactionService

    @PostMapping
    fun create(@PathVariable loanerId: Long, @RequestBody request: CreateTransactionRequest): Transaction {
        val loaner = loanerRepository.findByIdOrNull(loanerId) ?:
                throw throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val transaction = service.createTransaction(loaner, request.toCommand())
        return transaction
    }
}
