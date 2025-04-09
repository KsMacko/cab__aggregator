package com.internship.passenger_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WrappedResult<T> {
    private T result;
}
