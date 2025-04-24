package com.internship.driverservice.repo;

import com.internship.driverservice.entity.PaymentByCashConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentConfirmationRepo extends JpaRepository<PaymentByCashConfirmation, Long> {
}
