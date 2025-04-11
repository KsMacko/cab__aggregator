package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.entity.Fare;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FareMapper extends AbstractMapper<FareDto, Fare> {
    FareMapper converter = Mappers.getMapper(FareMapper.class);
}
