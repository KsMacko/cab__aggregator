package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.entity.PromoCode;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import static com.internship.ride_service.entity.Ride.Fields.createdAt;

@Mapper
public interface FareMapper extends AbstractMapper<FareDto, Fare> {
    FareMapper converter = Mappers.getMapper(FareMapper.class);
}
