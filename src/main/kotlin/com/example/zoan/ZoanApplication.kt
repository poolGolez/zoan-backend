package com.example.zoan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZoanApplication

fun main(args: Array<String>) {
	runApplication<ZoanApplication>(*args)
}
