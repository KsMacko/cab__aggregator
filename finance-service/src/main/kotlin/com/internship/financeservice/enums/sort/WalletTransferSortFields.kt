package com.internship.financeservice.enums.sort

import com.internship.financeservice.entity.FinancialOperation

enum class WalletTransferSortFields(val field: String) {
    ID(FinancialOperation.ID),
    CREATED_AT(FinancialOperation.CREATED_AT),
    AMOUNT(FinancialOperation.AMOUNT);
}