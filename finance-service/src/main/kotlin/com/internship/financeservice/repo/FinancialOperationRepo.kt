package com.internship.financeservice.repo

import com.internship.financeservice.entity.FinancialOperation
import org.springframework.data.jpa.repository.JpaRepository

interface FinancialOperationRepo : JpaRepository<FinancialOperation, Long> {
}