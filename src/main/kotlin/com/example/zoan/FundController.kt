package com.example.zoan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/funds")
class FundController {

    @Autowired
    lateinit var repository: FundRepository

    @GetMapping
    fun list(): List<FundDto> {
        val funds = repository.findAll()
        return funds.map { FundDto(it) }
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): FundDto {
        val fund = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return FundDto(fund)
    }
}
