package com.internship.financeservice.service.wallet

import com.internship.financeservice.dto.WalletDto
import com.internship.financeservice.dto.mapper.WalletMapper
import com.internship.financeservice.repo.DriverWalletRepo
import com.internship.financeservice.utils.WalletValidationManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommandWalletTransferService(
    private val walletRepo: DriverWalletRepo,
    private val walletValidatorManager: WalletValidationManager,
    private val walletMapper: WalletMapper
) {
    @Transactional
    fun createWallet(walletDto: WalletDto): WalletDto {
        walletValidatorManager.validateDriverIdUniqueness(walletDto.driverId)
        val wallet = walletMapper.toEntity(walletDto)
        return walletMapper.toDto(walletRepo.save(wallet))
    }
    @Transactional
    fun deleteWallet(id: Long) {
        walletValidatorManager.getWalletIfExists(id)
        walletRepo.deleteById(id)
    }
}