package com.internship.financeservice.dto.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class ResponseTransferDto (
    val id: Long,
    val driverId: Long,
    var date: LocalDateTime,
    var amount: BigDecimal,
    var remainingAmount: BigDecimal
)