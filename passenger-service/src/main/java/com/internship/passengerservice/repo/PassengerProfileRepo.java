package com.internship.passengerservice.repo;

import com.internship.passengerservice.entity.PassengerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PassengerProfileRepo extends JpaRepository<PassengerProfile, Long>, JpaSpecificationExecutor<PassengerProfile> {
    
    Boolean existsByPhoneIgnoreCase(String phone);
    Boolean existsByEmailIgnoreCase(String email);
}
