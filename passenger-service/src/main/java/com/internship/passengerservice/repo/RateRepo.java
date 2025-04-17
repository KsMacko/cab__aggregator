package com.internship.passengerservice.repo;

import com.internship.passengerservice.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RateRepo extends JpaRepository<Rate, Long> {
    @Query("select FLOOR(AVG(r.value)) from Rate r where r.passenger.profileId=:profileId")
    Byte findPassengerRatingByProfileId(Long profileId);
}
