package com.internship.finance_service.repo

import com.internship.finance_service.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PaymentRepo : JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {
}