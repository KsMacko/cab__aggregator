package com.internship.finance_service.service.finance

import com.internship.finance_service.dto.PaymentDto
import com.internship.finance_service.dto.WalletTransferDto
import com.internship.finance_service.dto.mapper.PaymentMapper
import com.internship.finance_service.dto.mapper.WalletTransferMapper
import com.internship.finance_service.entity.FinancialOperation
import com.internship.finance_service.repo.DriverWalletRepo
import com.internship.finance_service.repo.FinancialOperationRepo
import com.internship.finance_service.repo.PaymentRepo
import com.internship.finance_service.repo.WalletTransferRepo
import com.internship.finance_service.utils.FinanceValidationManager
import com.internship.finance_service.utils.WalletValidationManager
import org.springframework.beans.factory.annotation.Autowired
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