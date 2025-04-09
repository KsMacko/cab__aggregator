package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.entity.Ride;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RideMapper extends AbstractMapper<RideDto, Ride> {

    RideMapper converter = Mappers.getMapper(RideMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "CREATED")
    @Mapping(target = "startWaitingTime", ignore = true) // Эти поля не задаются при создании
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "driverId", ignore = true)
    Ride handleDto(RideDto rideDto);

    RideDto handleEntity(Ride ride);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(RideDto dto, @MappingTarget Ride entity);
}
