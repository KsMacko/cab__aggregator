package com.internship.financeservice.utils

import com.internship.financeservice.dto.response.WalletDto
import com.internship.financeservice.entity.DriverWallet
import com.internship.financeservice.repo.DriverWalletRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WalletValidationManager @Autowired constructor(
    private val walletRepo: DriverWalletRepo
) {
    fun getWalletIfExistsByDriverId(driverId: Long): DriverWallet {
        return walletRepo.findByDriverId(driverId)
            ?: throw RuntimeException("wallet.notFound")
    }
}