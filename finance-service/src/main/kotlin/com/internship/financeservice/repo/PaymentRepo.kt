package com.internship.financeservice.repo

import com.internship.financeservice.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PaymentRepo : JpaRepository<Payment, Long>, JpaSpecificationExecutor<Payment> {
}