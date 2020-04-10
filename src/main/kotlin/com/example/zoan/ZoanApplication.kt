package com.example.zoan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableTransactionManagement
class ZoanApplication

fun main(args: Array<String>) {
	runApplication<ZoanApplication>(*args)
}
