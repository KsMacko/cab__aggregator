package com.internship.driverservice.dto.mapper;

import com.internship.driverservice.dto.request.RequestProfileDto;
import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.entity.DriverProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {

    @Mapping(target = "profileId", ignore = true)
    DriverProfile handleDto(RequestProfileDto dto);

    ResponseProfileDto handleEntity(DriverProfile driverProfile, Integer rating);


    ResponseProfileDto handleEntity(DriverProfile driverProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(RequestProfileDto dto, @MappingTarget DriverProfile entity);
}
