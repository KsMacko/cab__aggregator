package com.internship.driverservice.dto.mapper;

import com.internship.driverservice.dto.request.RequestCarDto;
import com.internship.driverservice.dto.response.ResponseCarDto;
import com.internship.driverservice.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    @Mapping(target = "driverProfile", ignore = true)
    @Mapping(target = "id", ignore = true)
    Car handleDto(RequestCarDto dto);

    @Mapping(target = "driverId", source = "driverProfile.profileId")
    ResponseCarDto handleEntity(Car entity);
}
