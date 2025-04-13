package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.entity.Ride;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import static com.internship.ride_service.entity.Ride.Fields.createdAt;
import static com.internship.ride_service.entity.Ride.Fields.driverId;
import static com.internship.ride_service.entity.Ride.Fields.endTime;
import static com.internship.ride_service.entity.Ride.Fields.id;
import static com.internship.ride_service.entity.Ride.Fields.startTime;
import static com.internship.ride_service.entity.Ride.Fields.startWaitingTime;
import static com.internship.ride_service.entity.Ride.Fields.status;

@Mapper
public interface RideMapper {

    RideMapper converter = Mappers.getMapper(RideMapper.class);

    @Mapping(target = id, ignore = true)
    @Mapping(target = status, constant = "CREATED")
    @Mapping(target = startWaitingTime, ignore = true)
    @Mapping(target = createdAt, expression = "java(java.time.OffsetDateTime.now())")
    @Mapping(target = startTime, ignore = true)
    @Mapping(target = endTime, ignore = true)
    @Mapping(target = driverId, ignore = true)
    Ride handleDto(RideDto rideDto);

    RideDto handleEntity(Ride entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity( RideDto dto, @MappingTarget Ride entity);
}
