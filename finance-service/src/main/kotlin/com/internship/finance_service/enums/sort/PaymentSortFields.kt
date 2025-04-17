package com.internship.finance_service.enums.sort

import com.internship.finance_service.entity.FinancialOperation
import com.internship.finance_service.entity.Payment

enum class PaymentSortFields(val field: String) {
    ID(FinancialOperation.ID),
    CREATED_AT(FinancialOperation.CREATED_AT),
    AMOUNT(FinancialOperation.AMOUNT),
    PAYMENT_TYPE(Payment.PAYMENT_TYPE);
}