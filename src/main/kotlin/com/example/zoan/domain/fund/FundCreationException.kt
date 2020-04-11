package com.example.zoan.domain.fund

import com.example.zoan.ZoanException

class FundCreationException(
        cause: Exception,
        message: String = "Unable to create new fund"
) : ZoanException(message, cause)
