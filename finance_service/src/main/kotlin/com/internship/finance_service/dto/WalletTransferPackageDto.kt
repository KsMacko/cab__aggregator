package com.internship.finance_service.dto

data class WalletTransferPackageDto (
    var payments:List<WalletTransferDto>,
    var page:Int,
    var pageSize:Int,
    var totalCount:Int,
    var totalPages:Int
)