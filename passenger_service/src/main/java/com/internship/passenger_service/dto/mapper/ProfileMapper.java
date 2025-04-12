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
public interface ProfileMapper extends AbstractMapper<ProfileDto, PassengerProfile> {
    ProfileMapper converter = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = rate, source = "rate")
    ProfileDto handleEntity(PassengerProfile passengerProfile, Byte rate);
}
