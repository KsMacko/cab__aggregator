package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.PaymentDto
import com.internship.finance_service.entity.Payment
import org.springframework.stereotype.Component

@Component
interface PaymentMapper: AbstractMapper<Payment, PaymentDto>{
    override fun toDto(entity: Payment): PaymentDto {
        val financialOperation = entity.financialOperation

        return PaymentDto(
            id = entity.id,
            passengerId = entity.passengerId,
            date = financialOperation?.createdAt,
            amount = financialOperation?.amount,
            paymentType = entity.paymentType
        )
    }
}