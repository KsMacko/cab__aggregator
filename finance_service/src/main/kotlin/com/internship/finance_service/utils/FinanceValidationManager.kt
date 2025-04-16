package com.internship.finance_service.utils

import com.internship.finance_service.dto.PaymentDto
import com.internship.finance_service.dto.WalletTransferDto
import com.internship.finance_service.entity.DriverWallet
import com.internship.finance_service.entity.Payment
import com.internship.finance_service.entity.WalletTransfer
import com.internship.finance_service.enums.PaymentType
import com.internship.finance_service.repo.FinancialOperationRepo
import com.internship.finance_service.repo.PaymentRepo
import com.internship.finance_service.repo.WalletTransferRepo
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

    fun validatePayment(paymentDto: PaymentDto) {
        if (paymentDto.paymentType == PaymentType.CARD) {
            cardValidationManager.validateCardExistsForOwner(paymentDto.passengerId)
        }
    }

    fun validateWalletTransfer(walletTransferDto: WalletTransferDto): DriverWallet {
        val wallet = walletValidationManager.getWalletIfExistsByDriverId(walletTransferDto.driverId)
        if (wallet.balance < walletTransferDto.amount) {
            throw RuntimeException("wallet.transfer.invalidAmount")
        }
        return wallet
    }
}