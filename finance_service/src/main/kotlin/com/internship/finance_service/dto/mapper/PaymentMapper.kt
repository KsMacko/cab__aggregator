package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.PaymentDto
import com.internship.finance_service.entity.FinancialOperation
import com.internship.finance_service.entity.Payment
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface PaymentMapper {

    @Mapping(target = FinancialOperation.CREATED_AT, source = "financialOperation.createdAt")
    @Mapping(target = FinancialOperation.AMOUNT, source = "financialOperation.amount")
    fun toDto(entity: Payment): PaymentDto

    @Mapping(target = Payment.ID, ignore = true)
    @Mapping(target = "financialOperation.createdAt", source = FinancialOperation.CREATED_AT)
    @Mapping(target = "financialOperation.amount", source = FinancialOperation.CREATED_AT)
    fun toEntity(dto: PaymentDto): Payment
}