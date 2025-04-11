package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.entity.PromoCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromoCodeMapper extends AbstractMapper<PromoCodeDto, PromoCode> {
    PromoCodeMapper converter = Mappers.getMapper(PromoCodeMapper.class);
}
