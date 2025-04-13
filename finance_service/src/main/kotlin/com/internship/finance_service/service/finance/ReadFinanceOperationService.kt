package com.internship.finance_service.service.finance

import com.internship.finance_service.dto.PaymentDto
import com.internship.finance_service.dto.WalletTransferDto
import com.internship.finance_service.dto.mapper.PaymentMapper
import com.internship.finance_service.dto.mapper.WalletTransferMapper
import com.internship.finance_service.dto.transfer.request.PaymentFilterRequest
import com.internship.finance_service.dto.transfer.request.WalletTransferFilterRequest
import com.internship.finance_service.dto.transfer.response.PaymentPackageDto
import com.internship.finance_service.dto.transfer.response.WalletTransferPackageDto
import com.internship.finance_service.entity.Payment
import com.internship.finance_service.entity.WalletTransfer
import com.internship.finance_service.enums.sort.OrderDirection
import com.internship.finance_service.repo.PaymentRepo
import com.internship.finance_service.repo.WalletTransferRepo
import com.internship.finance_service.service.PageableObjectCreator
import com.internship.finance_service.service.specification.PaymentSpecification
import com.internship.finance_service.service.specification.WalletTransferSpecification
import com.internship.finance_service.utils.FinanceValidationManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadFinanceOperationService @Autowired constructor(
    private var paymentRepo: PaymentRepo,
    private var walletTransferRepo: WalletTransferRepo,
    private var financeValidationManager: FinanceValidationManager,
    private var paymentMapper: PaymentMapper,
    private var walletTransferMapper: WalletTransferMapper
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
    fun getPaymentById(id: Long): PaymentDto {
        return paymentMapper.toDto(financeValidationManager.getPaymentOperationIfExists(id))
    }

    @Transactional(readOnly = true)
    fun getWalletTransferById(id: Long): WalletTransferDto {
        return walletTransferMapper.toDto(financeValidationManager.getWalletTransferOperationIfExists(id))
    }
}