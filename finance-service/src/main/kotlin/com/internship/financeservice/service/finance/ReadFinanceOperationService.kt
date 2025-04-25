package com.internship.financeservice.service.finance

import com.internship.financeservice.dto.mapper.PaymentMapper
import com.internship.financeservice.dto.mapper.WalletTransferMapper
import com.internship.financeservice.dto.response.ResponsePaymentDto
import com.internship.financeservice.dto.response.ResponseTransferDto
import com.internship.financeservice.dto.transfer.request.PaymentFilterRequest
import com.internship.financeservice.dto.transfer.request.WalletTransferFilterRequest
import com.internship.financeservice.dto.transfer.response.PaymentPackageDto
import com.internship.financeservice.dto.transfer.response.WalletTransferPackageDto
import com.internship.financeservice.entity.Payment
import com.internship.financeservice.entity.WalletTransfer
import com.internship.financeservice.repo.PaymentRepo
import com.internship.financeservice.repo.WalletTransferRepo
import com.internship.financeservice.service.PageableObjectCreator
import com.internship.financeservice.service.specification.PaymentSpecification
import com.internship.financeservice.service.specification.WalletTransferSpecification
import com.internship.financeservice.utils.validation.FinanceValidationManager
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadFinanceOperationService(
    private val paymentRepo: PaymentRepo,
    private val walletTransferRepo: WalletTransferRepo,
    private val financeValidationManager: FinanceValidationManager,
    private val paymentMapper: PaymentMapper,
    private val walletTransferMapper: WalletTransferMapper
): PageableObjectCreator(){

    @Transactional(readOnly = true)
    fun findAllPayments(filter: PaymentFilterRequest): PaymentPackageDto {
        val spec = PaymentSpecification.createFilterSpecification(filter)

        val pageable = createPageableObjectByAllFields(filter.page, filter.size, filter.sortBy.toString(), filter.orderDirection)
        val page: Page<Payment> = paymentRepo.findAll(spec, pageable)
        val paymentsDto = page.content.map(paymentMapper::toDto)

        return PaymentPackageDto(
            payments = paymentsDto,
            page = page.number,
            size = page.size,
            totalPages = page.totalPages,
            totalElements = page.totalElements
        )
    }

    @Transactional(readOnly = true)
    fun findAllWalletTransfers(filter: WalletTransferFilterRequest): WalletTransferPackageDto {
        val spec = WalletTransferSpecification.createFilterSpecification(filter)

        val pageable = createPageableObjectByAllFields(filter.page, filter.size, filter.sortBy.toString(), filter.orderDirection)
        val page: Page<WalletTransfer> = walletTransferRepo.findAll(spec, pageable)
        val walletTransfersDto = page.content.map(walletTransferMapper::toDto)

        return WalletTransferPackageDto(
            walletTransfers = walletTransfersDto,
            page = page.number,
            size = page.size,
            totalPages = page.totalPages,
            totalElements = page.totalElements
        )
    }

    @Transactional(readOnly = true)
    fun getPaymentById(id: Long): ResponsePaymentDto =
        paymentMapper.toDto(financeValidationManager.getPaymentOperationIfExists(id))

    @Transactional(readOnly = true)
    fun getWalletTransferById(id: Long): ResponseTransferDto =
        walletTransferMapper.toDto(financeValidationManager.getWalletTransferOperationIfExists(id))
}