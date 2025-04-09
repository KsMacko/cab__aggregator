package com.internship.passenger_service.dto.mapper;

import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.entity.PassengerProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import static com.internship.passenger_service.dto.ProfileDto.Fields.rate;

@Mapper
public interface ProfileMapper {
    ProfileMapper converter = Mappers.getMapper(ProfileMapper.class);

    PassengerProfile handleDto(ProfileDto profileDto);
    ProfileDto handleEntity(PassengerProfile passengerProfile);
    @Mapping(target = rate, source = "rate")
    ProfileDto handleEntity(PassengerProfile passengerProfile, Byte rate);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileDto dto, @MappingTarget PassengerProfile entity);
}
