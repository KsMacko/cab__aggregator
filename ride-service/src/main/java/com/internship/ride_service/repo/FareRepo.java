package com.internship.ride_service.repo;

import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.enums.FareType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FareRepo extends MongoRepository<Fare, FareType> {
    Optional<Fare> findFareByType(FareType fareType);

    void deleteFareByType(FareType fareType);
}
