package com.internship.driver_service.repo;

import com.internship.driver_service.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepo extends JpaRepository<Rate, Long> {
    @Query("select FLOOR(AVG(r.value)) " +
            "from Rate r " +
            "where r.driver.profileId=:profileId ")
    Integer findDriverRatingByProfileId(@Param("profileId") Long profileId);
}
