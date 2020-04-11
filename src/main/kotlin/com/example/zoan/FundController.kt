package com.example.zoan

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/funds")
class FundController {

    @GetMapping
    fun list(): String {
        return "shit shoot"
    }
}
