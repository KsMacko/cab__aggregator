package com.internship.driver_service.repo;

import com.internship.driver_service.dto.RatingPerProfile;
import com.internship.driver_service.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepo extends JpaRepository<Rate, Long> {
    @Query("select FLOOR(AVG(r.value)) " +
            "from Rate r " +
            "where r.driver.profileId=:profileId " +
            "order by r.createdAt " +
            "desc limit: limit")
    Byte findDriverRatingByProfileId(@Param("profileId") Long profileId,
                                     @Param("limit") Integer limit);
    @Query(value = "SELECT r.driver.profileId AS profileId, FLOOR(AVG(r.value)) AS rating " +
            "FROM Rate r " +
            "WHERE r.driver.profileId IN (:profileIds) " +
            "GROUP BY r.driver.profileId " +
            "ORDER BY r.createdAt DESC " +
            "LIMIT :limit")
    List<RatingPerProfile> findRatingsByProfileIds(@Param("profileIds") List<Long> profileIds,
                                                   @Param("limit") Integer limit);

}
