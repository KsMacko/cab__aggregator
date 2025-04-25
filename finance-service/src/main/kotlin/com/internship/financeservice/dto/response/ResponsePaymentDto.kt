package com.internship.financeservice.dto.response

import com.internship.financeservice.enums.PaymentType
import java.math.BigDecimal
import java.time.LocalDateTime

data class ResponsePaymentDto (
    val id: Long,
    val passengerId: Long,
    var createdAt: LocalDateTime,
    var amount: BigDecimal,
    var paymentType: PaymentType
)