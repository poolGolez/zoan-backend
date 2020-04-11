package com.example.zoan.domain.borrower

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
}
