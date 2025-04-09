package com.internship.driver_service.dto.mapper;

public interface AbstractMapper<D, V> {
    V handleDto(D dto);
    D handleEntity(V entity);
    void updateEntity(D dto, V entity);
}
