package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.request.RequestFareDto;
import com.internship.ride_service.dto.request.RequestPromoCodeDto;
import com.internship.ride_service.dto.response.ResponseFareDto;
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
    Fare handleDto(RequestFareDto dto);
    ResponseFareDto handleEntity(Fare entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(RequestFareDto dto, @MappingTarget Fare entity);
}
