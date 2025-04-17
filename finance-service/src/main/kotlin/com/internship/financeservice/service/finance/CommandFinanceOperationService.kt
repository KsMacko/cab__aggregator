package com.internship.financeservice.service.finance

import com.internship.financeservice.dto.PaymentDto
import com.internship.financeservice.dto.WalletTransferDto
import com.internship.financeservice.dto.mapper.PaymentMapper
import com.internship.financeservice.dto.mapper.WalletTransferMapper
import com.internship.financeservice.entity.FinancialOperation
import com.internship.financeservice.repo.FinancialOperationRepo
import com.internship.financeservice.repo.PaymentRepo
import com.internship.financeservice.repo.WalletTransferRepo
import com.internship.financeservice.utils.FinanceValidationManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CommandFinanceOperationService (
    private val financeValidationManager: FinanceValidationManager,
    private val paymentMapper: PaymentMapper,
    private val walletTransferMapper: WalletTransferMapper,
    private val paymentRepo: PaymentRepo,
    private val walletTransferRepo: WalletTransferRepo,
    private val financialOperationRepo: FinancialOperationRepo
) {
    @Transactional
    fun createPayment(paymentDto: PaymentDto): PaymentDto {
        financeValidationManager.validatePayment(paymentDto)
        val payment = paymentMapper.toEntity(paymentDto).apply {
            this.financialOperation = createFinancialOperation(paymentDto.amount)
        }
        return paymentMapper.toDto(paymentRepo.save(payment))
    }

    @Transactional
    fun deletePayment(id: Long) {
        financeValidationManager.getPaymentOperationIfExists(id)
        paymentRepo.deleteById(id)
    }

    @Transactional
    fun createWalletTransfer(walletTransferDto: WalletTransferDto): WalletTransferDto {
        val wallet = financeValidationManager.validateWalletTransfer(walletTransferDto)
        val walletTransfer = walletTransferMapper.toEntity(walletTransferDto).apply {
            this.financialOperation = createFinancialOperation(walletTransferDto.amount)
        }
        walletTransfer.remainingAmount= wallet.balance.subtract(walletTransferDto.amount)
        return walletTransferMapper.toDto(walletTransferRepo.save(walletTransfer))
    }

    @Transactional
    fun deleteWalletTransfer(id: Long) {
        financeValidationManager.getWalletTransferOperationIfExists(id)
        walletTransferRepo.deleteById(id)
    }

    private fun createFinancialOperation(amount: BigDecimal): FinancialOperation =
        financialOperationRepo.save(FinancialOperation(amount))

}