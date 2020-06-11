package com.example.zoan.http.equity

import com.example.zoan.domain.equity.CreateTransactionParams
import com.example.zoan.domain.equity.Transaction
import com.example.zoan.domain.equity.TransactionRepository
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
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var service: TransactionService

    @GetMapping
    fun create(@PathVariable loanerId: Long): List<TransactionDto> {
        val loaner = fetchLoaner(loanerId)
        val transactions = transactionRepository.findByLoaner(loaner)
        return transactions.map { transaction -> TransactionDto(transaction) }
    }

    @PostMapping
    fun create(@PathVariable loanerId: Long, @RequestBody request: CreateTransactionRequest): TransactionDto {
        val loaner = fetchLoaner(loanerId)
        val transaction = service.createTransaction(loaner, request.toCommand())
        return TransactionDto(transaction)
    }

    private fun fetchLoaner(loanerId: Long) = loanerRepository.findByIdOrNull(loanerId)
            ?: throw throw ResponseStatusException(HttpStatus.NOT_FOUND)
}
