package com.internship.ride_service.dto.mapper;

import static com.internship.ride_service.entity.Ride.Fields.*;
import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.entity.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RideMapper extends AbstractMapper<RideDto, Ride> {

    RideMapper converter = Mappers.getMapper(RideMapper.class);

    @Mapping(target = id, ignore = true)
    @Mapping(target = status, constant = "CREATED")
    @Mapping(target = startWaitingTime, ignore = true)
    @Mapping(target = createdAt,  expression = "java(java.time.OffsetDateTime.now())")
    @Mapping(target = startTime, ignore = true)
    @Mapping(target = endTime, ignore = true)
    @Mapping(target = driverId, ignore = true)
    Ride handleDto(RideDto rideDto);
}
