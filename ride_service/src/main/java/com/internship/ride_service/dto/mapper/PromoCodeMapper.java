package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.entity.PromoCode;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import static com.internship.ride_service.entity.PromoCode.Fields.createdAt;

@Mapper
public interface PromoCodeMapper {
    PromoCodeMapper converter = Mappers.getMapper(PromoCodeMapper.class);
    @Mapping(target = createdAt, expression = "java(java.time.OffsetDateTime.now())")
    PromoCode handleDto(PromoCodeDto dto);

    PromoCodeDto handleEntity(PromoCode entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PromoCodeDto dto, @MappingTarget PromoCode entity);
}
