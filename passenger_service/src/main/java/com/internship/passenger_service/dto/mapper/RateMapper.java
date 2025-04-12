package com.internship.passenger_service.dto.mapper;

import com.internship.passenger_service.dto.RateDto;
import com.internship.passenger_service.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateMapper extends AbstractMapper<RateDto, Rate> {
    RateMapper converter = Mappers.getMapper(RateMapper.class);
}
