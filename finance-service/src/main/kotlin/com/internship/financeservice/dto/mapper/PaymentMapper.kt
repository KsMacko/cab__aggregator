package com.internship.financeservice.dto.mapper

import com.internship.financeservice.dto.response.ResponsePaymentDto
import com.internship.financeservice.entity.Payment
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface PaymentMapper {

    @Mapping(target = "createdAt", source = "financialOperation.createdAt")
    @Mapping(target = "amount", source = "financialOperation.amount")
    fun toDto(entity: Payment): ResponsePaymentDto
}