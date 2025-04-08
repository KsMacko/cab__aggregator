package com.internship.ride_service.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "promo_codes")
@Getter
@Setter
@FieldNameConstants
public class PromoCode {
    @Id
    private String code;
    private Byte discount;
    private LocalDate createdAt;
    private LocalDate validUntil;
}
