package com.internship.finance_service.repo

import com.internship.finance_service.entity.DriverWallet
import org.springframework.data.jpa.repository.JpaRepository

interface DriverWalletRepo: JpaRepository<DriverWallet, Long> {
}