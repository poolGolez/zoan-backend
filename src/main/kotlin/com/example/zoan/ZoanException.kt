package com.example.zoan

abstract class ZoanException(
        message: String,
        cause: Exception?
) : Exception(message, cause) {
    constructor(message: String) : this(message, null)
}
