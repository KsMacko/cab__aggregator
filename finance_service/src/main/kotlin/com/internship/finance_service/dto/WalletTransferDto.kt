package com.internship.finance_service.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class WalletTransferDto(
    val id: Long? = null,
    var date: LocalDate? = null,
    var time: LocalTime? = null,
    var amount: BigDecimal? = null,
    var remainingAmount: BigDecimal? = null
)