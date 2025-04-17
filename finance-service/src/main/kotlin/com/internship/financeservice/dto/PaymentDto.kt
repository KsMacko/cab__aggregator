package com.internship.financeservice.dto

import com.internship.financeservice.enums.PaymentType
import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentDto(
    val id: Long? = null,
    val passengerId: Long,
    var createdAt: LocalDateTime,
    var amount: BigDecimal,
    var paymentType: PaymentType
)
