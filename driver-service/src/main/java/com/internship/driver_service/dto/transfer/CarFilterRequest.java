package com.internship.driver_service.dto.transfer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.internship.driver_service.utils.validation.ValidationConstants.CAR_NUM_PATTERN;
import static com.internship.driver_service.utils.validation.ValidationConstants.LATIN_PATTERN;
import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_CAR_BRAND_LENGTH;
import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_ID_VALUE;
import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_PAGE_SIZE;
import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_PAGE_VALUE;
import static com.internship.driver_service.utils.validation.ValidationConstants.MIN_CAR_BRAND_LENGTH;

@Getter
@Setter
public class CarFilterRequest {
    @PositiveOrZero(message = "driverId must be positive or zero")
    @Max(value = MAX_ID_VALUE, message = "driver.id.maxValue")
    private Long driverId;
    @Pattern(regexp = CAR_NUM_PATTERN, message = "car.number.invalidInput")
    private String carNumber;
    private Boolean isCurrent;
    @Pattern(regexp = LATIN_PATTERN, message = "brand.invalidInput")
    @Size(min = MIN_CAR_BRAND_LENGTH, max = MAX_CAR_BRAND_LENGTH, message = "car.brand.size")
    private String brand;
    @PositiveOrZero(message = "page.positive")
    @Max(value = MAX_PAGE_VALUE, message = "page.max")
    private Integer page;
    @PositiveOrZero(message = "page.size.positive")
    @Max(value = MAX_PAGE_SIZE, message = "page.size.max")
    private Integer pageSize;
}