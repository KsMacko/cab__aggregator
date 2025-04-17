package com.internship.rideservice.repo;

import com.internship.rideservice.entity.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideRepo extends MongoRepository<Ride, String> {
}