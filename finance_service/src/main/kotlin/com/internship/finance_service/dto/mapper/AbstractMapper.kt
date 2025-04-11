package com.internship.finance_service.dto.mapper

import com.internship.finance_service.entity.Card
import org.mapstruct.BeanMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface AbstractMapper<E,D> {
    fun toDto(entity: E): D
    @Mapping(target = Card.ID, ignore = true)
    fun toEntity(dto: D): E
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    fun updateEntity(user: E, dto: D)
}