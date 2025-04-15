package com.internship.ride_service.repo;

import com.internship.ride_service.entity.PromoCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface PromoCodeRepo extends MongoRepository<PromoCode, String> {
    Optional<PromoCode> findByPromoCodeAndValidUntilAfter(String promoCode, OffsetDateTime currentDate);
}
