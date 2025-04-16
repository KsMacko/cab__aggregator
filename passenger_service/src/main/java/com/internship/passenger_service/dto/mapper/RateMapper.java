package com.internship.passenger_service.dto.mapper;

import com.internship.passenger_service.dto.RateDto;
import com.internship.passenger_service.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateMapper {
    Rate handleDto(RateDto dto);

    RateDto handleEntity(Rate entity);
}
