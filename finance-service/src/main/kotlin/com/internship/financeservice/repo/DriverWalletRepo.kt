package com.internship.financeservice.repo

import com.internship.financeservice.entity.DriverWallet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DriverWalletRepo : JpaRepository<DriverWallet, Long>, JpaSpecificationExecutor<DriverWallet> {
    fun findByDriverId(driverId: Long): DriverWallet?
    fun deleteByDriverId(driverId: Long)
}