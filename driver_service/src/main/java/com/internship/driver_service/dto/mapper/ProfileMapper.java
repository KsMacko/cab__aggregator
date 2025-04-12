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

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "driverAccount", ignore = true)
    @Mapping(target = "rates", ignore = true)
    @Mapping(target = "car", ignore = true)
    DriverProfile handleDto(ProfileDto dto);

    @Mapping(target = ProfileDto.Fields.rate, source = "rating")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "driverAccount", ignore = true)
    @Mapping(target = "rates", ignore = true)
    @Mapping(target = "car", ignore = true)
    ProfileDto handleEntity(DriverProfile driverProfile, Byte rating);


    ProfileDto handleEntity(DriverProfile driverProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileDto dto, @MappingTarget DriverProfile entity);
}
