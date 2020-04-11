package com.example.zoan

class FundCreationException(
        cause: Exception,
        message: String = "Unable to create new fund"
) : ZoanException(message, cause)
