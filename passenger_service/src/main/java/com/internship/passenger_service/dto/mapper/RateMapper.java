package com.internship.passenger_service.dto.mapper;

import com.internship.passenger_service.dto.response.ResponseRateDto;
import com.internship.passenger_service.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "passenger", ignore = true)
    Rate handleDto(ResponseRateDto dto);

    ResponseRateDto handleEntity(Rate entity);
}
