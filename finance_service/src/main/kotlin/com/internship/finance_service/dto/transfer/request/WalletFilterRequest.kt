package com.internship.finance_service.dto.transfer.request

import java.math.BigDecimal

data class WalletFilterRequest(
    val driverId: Long? = null,
    val minBalance: BigDecimal? = null,
    val maxBalance: BigDecimal? = null,
    val page: Int = 0,
    val size: Int = 10
)