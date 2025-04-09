package com.internship.driver_service.repo;

import com.internship.driver_service.dto.Projection;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverProfileRepo extends JpaRepository<DriverProfile, Long>, JpaSpecificationExecutor<DriverProfile> {
    DriverStatus getDriverStatusByProfileId(@Param("profileId") Long profileId);
    FareType getFareTypeByProfileId(@Param("profileId") Long profileId);
    Page<Projection> findAllDrivers(Specification<DriverProfile> spec, Pageable pageable);
}