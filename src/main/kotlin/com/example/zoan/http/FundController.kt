package com.example.zoan.http

import com.example.zoan.FundRepository
import com.example.zoan.FundService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/funds")
class FundController {

    @Autowired
    lateinit var repository: FundRepository

    @Autowired
    lateinit var fundService: FundService

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

    @PostMapping
    fun create(@RequestBody request: CreateFundRequest): FundDto {
        val params = request.toCommand()
        val fund = fundService.createFund(params)
        return FundDto(fund)
    }
}
