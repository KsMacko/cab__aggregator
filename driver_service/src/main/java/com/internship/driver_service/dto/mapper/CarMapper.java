package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.request.RequestCarDto;
import com.internship.driver_service.dto.response.ResponseCarDto;
import com.internship.driver_service.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    @Mapping(target = "driverProfile", ignore = true)
    Car handleDto(RequestCarDto dto);

    @Mapping(target = "driverId", source = "driverProfile.profileId")
    ResponseCarDto handleEntity(Car entity);
}
