package com.internship.driverservice.utils.validation;

public interface ValidationConstants {
    int MAX_ID_VALUE = 10000;
    int DEFAULT_PAGE_VALUE = 0;
    int MAX_PAGE_VALUE = 10000;
    int DEFAULT_PAGE_SIZE = 10;
    int MAX_PAGE_SIZE = 10000;
    String CYRILLIC_REGEX = "^[а-яА-ЯёЁ]+$";
    String PHONE_PATTERN = "^375(?:25|29|33|44)\\d{7}$";
    String CAR_NUM_PATTERN = "^[0-9]{4}[a-zA-Z]{2}[0-9]$";
    String LATIN_PATTERN = "^[A-Z0-9]{6,10}$";
    String DRIVER_STATUS_PATTERN = "FREE|DRIVING_TO_CLIENT|WAITING_FOR_CLIENT|IN_TRANSIT|ON_BREAK";
    String FARE_TYPE_PATTERN = "ECONOMY|COMFORT|BUSINESS";
    String PROFILE_SORT_FIELDS = "FIRST_NAME|LAST_NAME|CAR_NUMBER|FARE_TYPE|DRIVER_STATUS|PHONE";
    String ORDER_DIRECTION = "ASC|DESC";

    int MAX_NAME_LENGTH = 20;
    int MIN_CAR_BRAND_LENGTH = 1;
    int MAX_CAR_BRAND_LENGTH = 15;
    int MAX_CAR_NUMBER_LENGTH = 7;
    byte MIN_RATE = 1;
    byte MAX_RATE = 5;

}
