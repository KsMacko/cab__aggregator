package com.internship.financeservice.dto.response

import java.math.BigDecimal

data class WalletDto(
    val id: Long,
    val balance: BigDecimal,
    val driverId: Long
)