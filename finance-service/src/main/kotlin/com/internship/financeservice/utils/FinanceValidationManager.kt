package com.internship.financeservice.utils

import com.internship.financeservice.dto.request.RequestWalletTransferDto
import com.internship.financeservice.entity.DriverWallet
import com.internship.financeservice.entity.Payment
import com.internship.financeservice.entity.WalletTransfer
import com.internship.financeservice.enums.PaymentType
import com.internship.financeservice.repo.FinancialOperationRepo
import com.internship.financeservice.repo.PaymentRepo
import com.internship.financeservice.repo.WalletTransferRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FinanceValidationManager @Autowired constructor(
    private val financialOperationRepo: FinancialOperationRepo,
    private val paymentRepo: PaymentRepo,
    private val walletTransferRepo: WalletTransferRepo,
    private val cardValidationManager: CardValidationManager,
    private val walletValidationManager: WalletValidationManager
) {
    fun checkOperationIfExists(id: Long) {
        if (!financialOperationRepo.existsById(id)) {
            throw RuntimeException("operation.id.notFound")
        }
    }

    fun getPaymentOperationIfExists(id: Long): Payment {
        return paymentRepo.findById(id).orElseThrow {
            RuntimeException("operation.payment.notFound")
        }
    }

    fun getWalletTransferOperationIfExists(id: Long?): WalletTransfer {
        if (id == null) {
            throw RuntimeException("operation.id.notNull")
        }
        return walletTransferRepo.findById(id).orElseThrow {
            RuntimeException("operation.walletTransfer.notFound")
        }
    }

    fun validateOperationIdUniqueness(id: Long) {
        if (financialOperationRepo.existsById(id)) {
            throw RuntimeException("operation.id.alreadyExists")
        }
    }

    fun validatePayment(passengerId: Long) {
        cardValidationManager.validateCardExistsForOwner(passengerId)
    }

    fun validateWalletTransfer(requestWalletTransferDto: RequestWalletTransferDto): DriverWallet {
        val wallet = walletValidationManager.getWalletIfExistsByDriverId(requestWalletTransferDto.driverId)
        if (wallet.balance < requestWalletTransferDto.amount) {
            throw RuntimeException("wallet.transfer.invalidAmount")
        }
        return wallet
    }
}