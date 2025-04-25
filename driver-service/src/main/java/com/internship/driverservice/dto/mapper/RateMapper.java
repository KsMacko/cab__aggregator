package com.internship.driverservice.dto.mapper;

import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import com.internship.driverservice.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "driver", ignore = true)
    Rate handleDto(RequestRateDto dto);

    @Mapping(target = "recipientId", source = "driver.profileId")
    ResponseRateDto handleEntity(Rate entity);
}
