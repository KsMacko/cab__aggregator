package com.internship.financeservice.dto.mapper

import com.internship.financeservice.dto.PaymentDto
import com.internship.financeservice.entity.Payment
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface PaymentMapper {

    @Mapping(target = "createdAt", source = "financialOperation.createdAt")
    @Mapping(target = "amount", source = "financialOperation.amount")
    fun toDto(entity: Payment): PaymentDto

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "financialOperation.createdAt", ignore = true)
    @Mapping(target = "financialOperation.amount", source = "amount")
    fun toEntity(dto: PaymentDto): Payment
}