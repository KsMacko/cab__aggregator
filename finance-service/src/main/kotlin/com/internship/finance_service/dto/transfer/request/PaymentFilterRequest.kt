package com.internship.finance_service.dto.transfer.request

import com.internship.finance_service.enums.PaymentType
import com.internship.finance_service.enums.sort.OrderDirection
import com.internship.finance_service.enums.sort.PaymentSortFields
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