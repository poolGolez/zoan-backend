package com.example.zoan.http.util

import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class DateFormatter {
    companion object {
        fun format(date: LocalDate): String? {
            val formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY")
            return formatter.format(date)
        }
    }
}
