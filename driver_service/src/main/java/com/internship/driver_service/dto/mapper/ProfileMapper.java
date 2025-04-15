package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.entity.DriverProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {
    ProfileMapper converter = Mappers.getMapper(ProfileMapper.class);

    DriverProfile handleDto(ProfileDto dto);

    ProfileDto handleEntity(DriverProfile driverProfile, Integer rating);


    ProfileDto handleEntity(DriverProfile driverProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileDto dto, @MappingTarget DriverProfile entity);
}
