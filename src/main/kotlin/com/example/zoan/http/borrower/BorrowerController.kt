package com.example.zoan.http.borrower

import com.example.zoan.domain.borrower.Borrower
import com.example.zoan.domain.borrower.BorrowerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/borrowers")
class BorrowerController {

    @Autowired
    lateinit var repository: BorrowerRepository

    @GetMapping
    fun list(): MutableIterable<Borrower> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): Borrower {
        val borrower = repository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return borrower
    }

    @PostMapping
    fun create(@RequestBody request: CreateBorrowerRequest): Borrower {
        val borrower = request.toBorrower()
        repository.save(borrower)
        return borrower
    }
}
