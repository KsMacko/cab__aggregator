package com.internship.financeservice.utils.validation

import com.internship.financeservice.entity.DriverWallet
import com.internship.financeservice.repo.DriverWalletRepo
import com.internship.financeservice.utils.exceptions.ExceptionCodes
import com.internship.financeservice.utils.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WalletValidationManager @Autowired constructor(
    private val walletRepo: DriverWalletRepo
) {
    fun getWalletIfExistsByDriverId(driverId: Long): DriverWallet {
        return walletRepo.findByDriverId(driverId)
            ?: throw ResourceNotFoundException(ExceptionCodes.WALLET_NOT_FOUND.getCode())
    }
}