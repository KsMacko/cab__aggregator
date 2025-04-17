package com.internship.finance_service.repo

import com.internship.finance_service.entity.FinancialOperation
import org.springframework.data.jpa.repository.JpaRepository

interface FinancialOperationRepo : JpaRepository<FinancialOperation, Long> {
}