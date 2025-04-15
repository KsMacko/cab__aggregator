package com.internship.ride_service.repo;

import com.internship.ride_service.entity.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideRepo extends MongoRepository<Ride, String> {
}