package com.internship.driverservice.dto.mapper;

import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import com.internship.driverservice.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateMapper {
    Rate handleDto(RequestRateDto dto);

    ResponseRateDto handleEntity(Rate entity);
}
