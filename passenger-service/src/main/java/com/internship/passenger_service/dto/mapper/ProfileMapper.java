package com.internship.passenger_service.dto.mapper;

import com.internship.passenger_service.dto.request.RequestProfileDto;
import com.internship.passenger_service.dto.response.ResponseProfileDto;
import com.internship.passenger_service.entity.PassengerProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper{
    @Mapping(target = "profileId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rates", ignore = true)
    PassengerProfile handleDto(RequestProfileDto dto);

    ResponseProfileDto handleEntity(PassengerProfile passengerProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "profileId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "rates", ignore = true)
    void updateEntity(RequestProfileDto dto, @MappingTarget PassengerProfile entity);


}
