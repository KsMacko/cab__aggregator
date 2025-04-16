package com.internship.passenger_service.repo;

import com.internship.passenger_service.entity.PassengerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PassengerProfileRepo extends JpaRepository<PassengerProfile, Long>, JpaSpecificationExecutor<PassengerProfile> {

    @Query("SELECT p.activatedPromoCodeId FROM PassengerProfile p WHERE p.profileId = :passengerId")
    Optional<Long> findActivatedPromoCodeIdByPassenger(@Param("passengerId") Long passengerId);
    Boolean existsByPhoneIgnoreCase(String phone);
    Boolean existsByEmailIgnoreCase(String email);
}
