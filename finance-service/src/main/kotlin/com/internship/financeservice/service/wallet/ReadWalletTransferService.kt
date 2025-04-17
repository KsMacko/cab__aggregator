package com.internship.financeservice.service.wallet

import com.internship.financeservice.dto.WalletDto
import com.internship.financeservice.dto.mapper.WalletMapper
import com.internship.financeservice.dto.transfer.request.WalletFilterRequest
import com.internship.financeservice.dto.transfer.response.WalletPackageDto
import com.internship.financeservice.entity.DriverWallet
import com.internship.financeservice.repo.DriverWalletRepo
import com.internship.financeservice.service.PageableObjectCreator
import com.internship.financeservice.service.specification.DriverWalletSpecification
import com.internship.financeservice.utils.WalletValidationManager
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadWalletTransferService (
    private val walletRepo: DriverWalletRepo,
    private val walletMapper: WalletMapper,
    private val walletValidatorManager: WalletValidationManager
): PageableObjectCreator() {

    @Transactional(readOnly = true)
    fun findAllWallets(filter: WalletFilterRequest): WalletPackageDto {
        val spec = DriverWalletSpecification.createFilterSpecification(filter)

        val pageable = createPageableObjectByAllFields(
            page = filter.page,
            size = filter.size
        )
        val page: Page<DriverWallet> = walletRepo.findAll(spec, pageable)
        val walletsDto = page.content.map(walletMapper::toDto)

        return WalletPackageDto(
            wallets = walletsDto,
            page = page.number,
            size = page.size,
            totalPages = page.totalPages,
            totalElements = page.totalElements
        )
    }

    @Transactional(readOnly = true)
    fun getWalletById(id: Long): WalletDto =
        walletMapper.toDto(walletValidatorManager.getWalletIfExists(id))
}