package com.internship.financeservice.dto.transfer.request

import com.internship.financeservice.enums.sort.OrderDirection
import com.internship.financeservice.enums.sort.WalletTransferSortFields
import java.time.LocalDateTime

data class WalletTransferFilterRequest(
    val createdAt: LocalDateTime? = null,
    val driverId: Long? = null,
    val page: Int = 0,
    val size: Int = 10,
    val orderDirection: OrderDirection = OrderDirection.DESC,
    val sortBy: WalletTransferSortFields = WalletTransferSortFields.CREATED_AT
)