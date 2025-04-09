package com.internship.ride_service.dto.mapper;

public interface AbstractMapper<D, E> {
    E handleDto(D dto);
    D handleEntity(E entity);
    void updateEntity(D dto, E entity);
}