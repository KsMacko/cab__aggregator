package com.internship.rideservice.dto.mapper;

import com.internship.rideservice.dto.response.ResponseRideDto;
import com.internship.rideservice.dto.request.RequestRideDto;
import com.internship.rideservice.entity.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RideMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "CREATED")
    @Mapping(target = "startWaitingTime", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "driverId", ignore = true)
    @Mapping(target = "price", ignore = true)
    Ride handleDto(RequestRideDto rideDto);

    ResponseRideDto handleEntity(Ride entity);

}
