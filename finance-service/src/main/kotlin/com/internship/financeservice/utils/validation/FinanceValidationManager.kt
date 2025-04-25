package com.internship.financeservice.utils.validation

import com.internship.financeservice.dto.request.RequestWalletTransferDto
import com.internship.financeservice.entity.DriverWallet
import com.internship.financeservice.entity.Payment
import com.internship.financeservice.entity.WalletTransfer
import com.internship.financeservice.repo.FinancialOperationRepo
import com.internship.financeservice.repo.PaymentRepo
import com.internship.financeservice.repo.WalletTransferRepo
import com.internship.financeservice.utils.exceptions.ExceptionCodes
import com.internship.financeservice.utils.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FinanceValidationManager @Autowired constructor(
    private val paymentRepo: PaymentRepo,
    private val walletTransferRepo: WalletTransferRepo,
    private val walletValidationManager: WalletValidationManager
) {
    fun getPaymentOperationIfExists(id: Long): Payment {
        return paymentRepo.findById(id).orElseThrow {
            ResourceNotFoundException(ExceptionCodes.PAYMENT_NOT_FOUND.getCode())
        }
    }

    fun getWalletTransferOperationIfExists(id: Long): WalletTransfer {
        return walletTransferRepo.findById(id).orElseThrow {
            ResourceNotFoundException(ExceptionCodes.WALLET_TRANSFER_NOT_FOUND.getCode())
        }
    }

    fun validateWalletTransfer(requestWalletTransferDto: RequestWalletTransferDto): DriverWallet {
        val wallet = walletValidationManager.getWalletIfExistsByDriverId(requestWalletTransferDto.driverId)
        if (wallet.balance < requestWalletTransferDto.amount) {
            throw ResourceNotFoundException(ExceptionCodes.WALLET_TRANSFER_INVALID_AMOUNT.getCode())
        }
        return wallet
    }
}