package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.entity.Fare;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FareMapper {
    @Mapping(target = "createdAt", ignore = true)
    Fare handleDto(FareDto dto);
    FareDto handleEntity(Fare entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(FareDto dto, @MappingTarget Fare entity);
}
