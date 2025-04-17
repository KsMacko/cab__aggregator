package com.internship.financeservice.enums.sort

import com.internship.financeservice.entity.FinancialOperation
import com.internship.financeservice.entity.Payment

enum class PaymentSortFields(val field: String) {
    ID(FinancialOperation.ID),
    CREATED_AT(FinancialOperation.CREATED_AT),
    AMOUNT(FinancialOperation.AMOUNT),
    PAYMENT_TYPE(Payment.PAYMENT_TYPE);
}