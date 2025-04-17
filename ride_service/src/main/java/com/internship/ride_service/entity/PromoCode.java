package com.internship.ride_service.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "promo_codes")
@Getter
@Setter
@FieldNameConstants
public class PromoCode {
    @Id
    private String id;
    private String promoCode;
    private Byte discount;
    private Date createdAt;
    private LocalDate validUntil;
}
