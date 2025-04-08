package com.internship.ride_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.enums.RideStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document(collection = "rides")
@Getter
@Setter
@FieldNameConstants
public class Ride {
    @Id
    private String id;
    private Long passengerId;
    private Long driverId;
    private String promoCode;
    private String startLocation;
    private List<String> endLocation;
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startWaitingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private Float distance;
    private RideStatus status;
    private FareType fareType;
    private BigDecimal price;
}
