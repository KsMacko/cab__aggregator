package com.internship.financeservice.dto.mapper

import com.internship.financeservice.dto.request.RequestCardDto
import com.internship.financeservice.dto.response.ResponseCardDto
import com.internship.financeservice.entity.Card
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface CardMapper {
    fun toDto(entity: Card): ResponseCardDto

    @Mapping(target = Card.ID, ignore = true)
    @Mapping(target = Card.FINANCIAL_OPERATION, ignore = true)
    fun toEntity(dto: RequestCardDto): Card
}