package com.internship.rideservice.entity;

import com.internship.rideservice.enums.FareType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Document(collection = "fares")
@Getter
@Setter
@FieldNameConstants
public class Fare {
    @Id
    @Indexed(unique = true)
    private FareType type;
    private BigDecimal minPrice;
    private Integer freeWaiting;
    private BigDecimal paidWaitingPrice;
    private BigDecimal pricePerKm;
    private BigDecimal pricePerMin;
    private LocalDateTime createdAt=LocalDateTime.now();
}
