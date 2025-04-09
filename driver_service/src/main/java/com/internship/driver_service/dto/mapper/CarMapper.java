package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.entity.Car;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper extends AbstractMapper<CarDto, Car>{
    CarMapper converter = Mappers.getMapper(CarMapper.class);

    Car handleDto(CarDto carDto);
    CarDto handleEntity(Car car);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CarDto dto, @MappingTarget Car entity);
}
