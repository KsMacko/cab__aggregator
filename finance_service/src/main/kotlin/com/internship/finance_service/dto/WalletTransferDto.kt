package com.internship.finance_service.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class WalletTransferDto(
    val id: Long? = null,
    val driverId: Long? = null,
    var date: LocalDateTime? = null,
    var amount: BigDecimal? = null,
    var remainingAmount: BigDecimal? = null
)