package com.internship.passenger_service.repo;

import com.internship.passenger_service.entity.PassengerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerAccountRepo extends JpaRepository<PassengerAccount, Long> {
}
