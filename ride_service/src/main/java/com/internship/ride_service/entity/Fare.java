package com.internship.ride_service.entity;

import com.internship.ride_service.enums.FareType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "fares")
@Getter
@Setter
public class Fare {
    @Id
    @Indexed(unique = true)
    private FareType type;
    private BigDecimal minPrice;
    private Integer freeWaiting;
    private BigDecimal paidWaitingPrice;
    private BigDecimal pricePerKm;
    private BigDecimal pricePerMin;
}
