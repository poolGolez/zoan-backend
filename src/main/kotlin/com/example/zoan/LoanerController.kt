package com.example.zoan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/loaners")
class LoanerController
{
    @Autowired
    lateinit var repository: LoanerRepository

    @GetMapping
    fun list(): Iterable<Loaner> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): Any {
        return repository.findById(id)
    }
}
