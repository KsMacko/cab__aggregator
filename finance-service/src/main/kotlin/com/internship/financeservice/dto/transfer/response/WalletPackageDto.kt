package com.internship.financeservice.dto.transfer.response

import com.internship.financeservice.dto.WalletDto

data class WalletPackageDto(
    val wallets: List<WalletDto>,
    val page: Int = 0,
    val size: Int = 10,
    val totalPages: Int = 0,
    val totalElements: Long = 0
)