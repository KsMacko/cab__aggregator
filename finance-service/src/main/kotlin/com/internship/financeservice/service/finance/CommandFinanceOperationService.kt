package com.internship.financeservice.service.finance

import com.internship.commonevents.event.ConfirmedPaymentRequest
import com.internship.financeservice.dto.request.RequestWalletTransferDto
import com.internship.financeservice.dto.mapper.WalletTransferMapper
import com.internship.financeservice.dto.response.ResponseTransferDto
import com.internship.financeservice.entity.FinancialOperation
import com.internship.financeservice.entity.Payment
import com.internship.financeservice.enums.PaymentType
import com.internship.financeservice.repo.DriverWalletRepo
import com.internship.financeservice.repo.FinancialOperationRepo
import com.internship.financeservice.repo.PaymentRepo
import com.internship.financeservice.repo.WalletTransferRepo
import com.internship.financeservice.utils.Constants.Companion.SALARY_PERCENT
import com.internship.financeservice.utils.FinanceValidationManager
import com.internship.financeservice.utils.WalletValidationManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class CommandFinanceOperationService (
    private val financeValidationManager: FinanceValidationManager,
    private val walletTransferMapper: WalletTransferMapper,
    private val paymentRepo: PaymentRepo,
    private val walletTransferRepo: WalletTransferRepo,
    private val financialOperationRepo: FinancialOperationRepo,
    private val walletValidationManager: WalletValidationManager,
    private val walletRepo: DriverWalletRepo
) {
    @Transactional
    fun createPaymentByCash(event: ConfirmedPaymentRequest){
        createPayment(PaymentType.CASH, event)
    }
    @Transactional
    fun createPaymentByCard(event: ConfirmedPaymentRequest){
        createPayment(PaymentType.CARD, event)
    }

    @Transactional
    fun createWalletTransfer(requestWalletTransferDto: RequestWalletTransferDto): ResponseTransferDto {
        val wallet = financeValidationManager.validateWalletTransfer(requestWalletTransferDto)
        val walletTransfer = walletTransferMapper.toEntity(requestWalletTransferDto).apply {
            this.financialOperation = createFinancialOperation(requestWalletTransferDto.amount)
        }
        wallet.balance = wallet.balance.subtract(requestWalletTransferDto.amount)
        walletTransfer.remainingAmount = wallet.balance
        walletRepo.save(wallet)
        return walletTransferMapper.toDto(walletTransferRepo.save(walletTransfer))
    }
    private fun createFinancialOperation(amount: BigDecimal): FinancialOperation =
        financialOperationRepo.save(FinancialOperation(amount))

    private fun createPayment(paymentType: PaymentType, event: ConfirmedPaymentRequest) {
        val financialOperation = createFinancialOperation(event.amount)
        val payment = Payment(event.passengerId, paymentType, financialOperation)
        calculateDriverDeduction(event.amount, event.driverId)
        financialOperationRepo.save(financialOperation)
        paymentRepo.save(payment)
    }
    private fun calculateDriverDeduction(amount: BigDecimal, driverId: Long){
        val driverWallet = walletValidationManager.getWalletIfExistsByDriverId(driverId)
        driverWallet.balance += (amount.multiply(SALARY_PERCENT))
        walletRepo.save(driverWallet)
    }

}