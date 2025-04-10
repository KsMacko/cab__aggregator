package com.internship.ride_service.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import static com.internship.ride_service.entity.Ride.Fields.createdAt;

@Mapper
public interface AbstractMapper<D, E> {
    @Mapping(target = createdAt,  expression = "java(java.time.OffsetDateTime.now())")
    E handleDto(D dto);
    D handleEntity(E entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(D dto, @MappingTarget E entity);
}