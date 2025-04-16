package com.internship.finance_service.utils

import com.internship.finance_service.entity.DriverWallet
import com.internship.finance_service.repo.DriverWalletRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WalletValidationManager @Autowired constructor(
    private val walletRepo: DriverWalletRepo
) {

    fun getWalletIfExists(id: Long): DriverWallet {
        return walletRepo.findById(id).orElseThrow {
            IllegalArgumentException("wallet.notFound")
        }
    }

    fun validateDriverIdUniqueness(driverId: Long) {
        if (walletRepo.existsByDriverId(driverId)) {
            throw IllegalArgumentException("wallet.alreadyExists")
        }
    }

    fun getWalletIfExistsByDriverId(driverId: Long): DriverWallet {
        return walletRepo.findByDriverId(driverId)
            ?: throw RuntimeException("wallet.notFound")
    }
}