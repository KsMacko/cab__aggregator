package com.internship.passenger_service.dto.mapper;

import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.entity.PassengerProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper converter = Mappers.getMapper(ProfileMapper.class);

    PassengerProfile handleDto(ProfileDto profileDto);
    ProfileDto handleEntity(PassengerProfile passengerProfile);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileDto dto, @MappingTarget PassengerProfile entity);
}
