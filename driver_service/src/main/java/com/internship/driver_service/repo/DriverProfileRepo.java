package com.internship.driver_service.repo;

import com.internship.driver_service.dto.Projection;
import com.internship.driver_service.entity.DriverProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DriverProfileRepo extends JpaRepository<DriverProfile, Long>, JpaSpecificationExecutor<DriverProfile> {
    Boolean existsByPhone(String phone);

    Page<Projection> findAllDrivers(Specification<DriverProfile> spec, Pageable pageable);
}