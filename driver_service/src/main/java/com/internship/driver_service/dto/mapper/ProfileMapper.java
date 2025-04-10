package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.entity.DriverProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper extends AbstractMapper<ProfileDto, DriverProfile> {
    ProfileMapper converter = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = ProfileDto.Fields.rate, source = "rating")
    ProfileDto handleEntity(DriverProfile driverProfile, Byte rating);
}
