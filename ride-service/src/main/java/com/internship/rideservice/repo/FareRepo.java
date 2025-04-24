package com.internship.rideservice.repo;

import com.internship.rideservice.entity.Fare;
import com.internship.rideservice.enums.FareType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FareRepo extends MongoRepository<Fare, FareType> {
    Optional<Fare> findFareByType(FareType fareType);

    void deleteFareByType(FareType fareType);
    Boolean existsFareByType(FareType fareType);
}
