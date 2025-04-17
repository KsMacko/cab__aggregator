package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.request.RequestPromoCodeDto;
import com.internship.ride_service.dto.response.ResponsePromoCodeDto;
import com.internship.ride_service.entity.PromoCode;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PromoCodeMapper {

    DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    @Mapping(target = "validUntil", source = "validUntil")
    PromoCode handleDto(RequestPromoCodeDto dto);

    @Mapping(target = "validUntil", source = "validUntil")
    ResponsePromoCodeDto handleEntity(PromoCode entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "validUntil", source = "validUntil")
    void updateEntity(RequestPromoCodeDto dto, @MappingTarget PromoCode entity);

    default String mapLocalDateToString(LocalDate value) {
        return value != null ? value.format(FORMATTER) : null;
    }

    default LocalDate mapStringToLocalDate(String value) {
        try {
            return value != null ? LocalDate.parse(value, FORMATTER) : null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + value, e);
        }
    }
}
