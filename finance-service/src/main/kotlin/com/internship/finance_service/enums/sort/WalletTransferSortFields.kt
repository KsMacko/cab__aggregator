package com.internship.finance_service.enums.sort

import com.internship.finance_service.entity.FinancialOperation

enum class WalletTransferSortFields(val field: String) {
    ID(FinancialOperation.ID),
    CREATED_AT(FinancialOperation.CREATED_AT),
    AMOUNT(FinancialOperation.AMOUNT);
}