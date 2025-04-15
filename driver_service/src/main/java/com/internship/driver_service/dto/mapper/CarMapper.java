package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    CarMapper converter = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "driverProfile", ignore = true)
    Car handleDto(CarDto dto);

    @Mapping(target = "driverId", source = "driverProfile.profileId")
    CarDto handleEntity(Car entity);
}
