package com.internship.financeservice.dto.transfer.request

import com.internship.financeservice.enums.PaymentType
import com.internship.financeservice.enums.sort.OrderDirection
import com.internship.financeservice.enums.sort.PaymentSortFields
import java.time.LocalDateTime

data class PaymentFilterRequest(
    var createdAt: LocalDateTime? = null,
    var passengerId: Long? = null,
    var paymentType: PaymentType? = null,
    val page: Int = 0,
    val size: Int = 10,
    val orderDirection: OrderDirection = OrderDirection.DESC,
    val sortBy: PaymentSortFields = PaymentSortFields.CREATED_AT
)