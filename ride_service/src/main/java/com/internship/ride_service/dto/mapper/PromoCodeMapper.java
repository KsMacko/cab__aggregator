package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.entity.PromoCode;
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
import static com.internship.ride_service.entity.Ride.Fields.startTime;

@Mapper
public interface PromoCodeMapper extends AbstractMapper<PromoCodeDto, PromoCode> {
    PromoCodeMapper converter = Mappers.getMapper(PromoCodeMapper.class);
}
