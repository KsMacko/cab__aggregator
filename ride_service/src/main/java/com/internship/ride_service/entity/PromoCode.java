package com.internship.ride_service.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "promo_codes")
@Getter
@Setter
public class PromoCode {
    @Id
    private String code;
    private Byte discount;
    private LocalDate createdAt;
    private LocalDate validUntil;
}
