package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.CardDto
import com.internship.finance_service.entity.Card
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface CardMapper {
    fun toDto(entity: Card): CardDto

    @Mapping(target = Card.ID, ignore = true)
    @Mapping(target = Card.FINANCIAL_OPERATION, ignore = true)
    fun toEntity(dto: CardDto): Card
}