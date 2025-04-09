package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.entity.DriverProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper converter = Mappers.getMapper(ProfileMapper.class);

    DriverProfile handleDto(ProfileDto profileDto);
    @Mapping(target = ProfileDto.Fields.rate, source = "rating")
    ProfileDto handleEntity(DriverProfile driverProfile, Byte rating);
    ProfileDto handleEntity(DriverProfile driverProfile);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileDto dto, @MappingTarget DriverProfile entity);
}
