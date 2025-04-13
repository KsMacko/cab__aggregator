package com.internship.ride_service.dto.mapper;

import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.entity.Fare;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import static com.internship.ride_service.entity.Fare.Fields.createdAt;

@Mapper
public interface FareMapper {
    FareMapper converter = Mappers.getMapper(FareMapper.class);
    @Mapping(target = createdAt, expression = "java(java.time.OffsetDateTime.now())")
    Fare handleDto(FareDto dto);

    FareDto handleEntity(Fare entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity( FareDto dto, @MappingTarget Fare entity);
}
