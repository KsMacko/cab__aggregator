package com.internship.driver_service.dto.mapper;

import com.internship.driver_service.dto.RateDto;
import com.internship.driver_service.entity.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateMapper extends AbstractMapper<RateDto, Rate> {
    RateMapper converter = Mappers.getMapper(RateMapper.class);

    Rate handleDto(RateDto dto);
    RateDto handleEntity(Rate rate);
}
