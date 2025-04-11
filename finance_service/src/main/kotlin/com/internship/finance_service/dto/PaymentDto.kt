package com.internship.finance_service.dto

import com.internship.finance_service.enums.PaymentType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.LocalTime

data class PaymentDto (
    val id: Long? = null,
    val passengerId: Long? = null,
    var date: LocalDateTime? = null,
    var time: LocalTime? = null,
    var amount: BigDecimal? = null,
    var paymentType : PaymentType? = null
)
