package com.internship.finance_service.service.wallet

import com.internship.finance_service.dto.WalletDto
import com.internship.finance_service.dto.mapper.WalletMapper
import com.internship.finance_service.dto.transfer.request.WalletFilterRequest
import com.internship.finance_service.dto.transfer.response.WalletPackageDto
import com.internship.finance_service.entity.DriverWallet
import com.internship.finance_service.repo.DriverWalletRepo
import com.internship.finance_service.service.PageableObjectCreator
import com.internship.finance_service.service.specification.DriverWalletSpecification
import com.internship.finance_service.utils.WalletValidationManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadWalletTransferService @Autowired constructor(
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
    fun getWalletById(id: Long): WalletDto {
        val wallet = walletValidatorManager.getWalletIfExists(id)
        return walletMapper.toDto(wallet)
    }
}