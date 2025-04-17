package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.request.RequestRateDto;
import com.internship.driver_service.dto.response.ResponseRateDto;
import com.internship.driver_service.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateMapper {
    Rate handleDto(RequestRateDto dto);

    ResponseRateDto handleEntity(Rate entity);
}
