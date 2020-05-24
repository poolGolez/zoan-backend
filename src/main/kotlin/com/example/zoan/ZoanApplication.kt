package com.example.zoan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement


@SpringBootApplication
@EnableTransactionManagement
class ZoanApplication

fun main(args: Array<String>) {
    runApplication<ZoanApplication>(*args)
}
