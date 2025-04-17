package com.internship.passenger_service.utils;

public interface ValidationConstants {
    int MAX_ID_VALUE = 100;
    int DEFAULT_PAGE_VALUE = 0;
    int MAX_PAGE_VALUE = 100;
    int DEFAULT_PAGE_SIZE = 10;
    int MAX_PAGE_SIZE = 10000;
    String CYRILLIC_REGEX = "^[а-яА-ЯёЁ]+$";
    String PHONE_PATTERN = "^375(?:25|29|33|44)\\d{7}$";
    String PROFILE_SORT_FIELDS = "FIRST_NAME|EMAIL|PHONE";
    String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String ORDER_DIRECTION = "ASC|DESC";

    int MAX_NAME_LENGTH = 20;
    int MAX_EMAIL_LENGTH = 50;
    byte MIN_RATE = 1;
    byte MAX_RATE = 5;

}
