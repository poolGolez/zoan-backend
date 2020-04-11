package com.example.zoan.http

import com.example.zoan.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/loaners")
class LoanerController {
    @Autowired
    lateinit var factory: LoanerFactory

    @Autowired
    lateinit var repository: LoanerRepository

    @GetMapping
    fun list(): Iterable<Loaner> {
        return repository.findAll()
    }

    @Throws(ResponseStatusException::class)
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): Loaner {
        return repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun create(@RequestBody request: CreateLoanerRequest): Loaner {
        val loaner = factory.createFromRequest(request)
        repository.save(loaner)
        return loaner
    }

    @PatchMapping("/{id}")
    fun patch(
            @PathVariable id: Long,
            @RequestBody request: UpdateLoanerRequest
    ): Loaner {
        var loaner = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        loaner = factory.updateFromRequest(loaner, request)
        repository.save(loaner)
        return loaner
    }
}
