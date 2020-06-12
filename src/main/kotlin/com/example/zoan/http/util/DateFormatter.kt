package com.example.zoan.http.util

import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class DateFormatter {
    companion object {
        val formatter = DateTimeFormatter.ofPattern("M-d-yyyy")

        fun format(date: LocalDate): String? {
            return formatter.format(date)
        }

        fun parse(dateString: String): LocalDate {
            return LocalDate.parse(dateString, formatter)
        }
    }
}
