package com.internship.finance_service.dto.transfer.response

import com.internship.finance_service.dto.PaymentDto

data class PaymentPackageDto (
    var payments: List<PaymentDto>,
    val page: Int = 0,
    val size: Int= 10,
    val totalPages: Int = 0,
    val totalElements: Long = 0
)