package com.example.zoan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/loaners")
class LoanerController
{
    @Autowired
    lateinit var repository: LoanerRepository

    @GetMapping
    fun list(): Iterable<Loaner> {
        return repository.findAll();
    }
}
