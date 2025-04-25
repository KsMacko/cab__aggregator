package com.internship.financeservice.dto.request

import java.math.BigDecimal

data class RequestWalletTransferDto(
    val driverId: Long,
    var amount: BigDecimal
)