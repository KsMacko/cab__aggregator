package com.internship.ride_service.entity;

import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.enums.RideStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
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
    private OffsetDateTime createdAt;
    private OffsetTime startWaitingTime;
    private OffsetTime startTime;
    private OffsetTime endTime;
    private Float distance;
    private RideStatus status;
    private FareType fareType;
    private BigDecimal price;
}
