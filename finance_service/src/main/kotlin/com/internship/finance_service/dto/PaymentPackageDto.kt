package com.internship.finance_service.dto

data class PaymentPackageDto (
    var payments: List<PaymentDto>,
    var page: Int,
    var pageSize: Int,
    var totalCount: Int,
    var totalPages: Int
)