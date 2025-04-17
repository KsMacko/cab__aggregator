package com.internship.rideservice.dto.mapper;

import com.internship.rideservice.dto.request.RequestPromoCodeDto;
import com.internship.rideservice.dto.response.ResponsePromoCodeDto;
import com.internship.rideservice.entity.PromoCode;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PromoCodeMapper {

    DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
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
