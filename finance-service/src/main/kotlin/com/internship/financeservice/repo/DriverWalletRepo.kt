package com.internship.financeservice.repo

import com.internship.financeservice.entity.DriverWallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DriverWalletRepo : JpaRepository<DriverWallet, Long>, JpaSpecificationExecutor<DriverWallet> {
    fun existsByDriverId(driverId: Long): Boolean
    fun findByDriverId(driverId: Long): DriverWallet?
}