package com.internship.driver_service.repo;

import com.internship.driver_service.entity.DriverAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverAccountRepo extends JpaRepository<DriverAccount, Long> {

}
