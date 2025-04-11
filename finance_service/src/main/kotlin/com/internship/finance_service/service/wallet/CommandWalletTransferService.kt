package com.internship.finance_service.service.wallet

import com.internship.finance_service.dto.WalletDto
import com.internship.finance_service.dto.mapper.WalletMapper
import com.internship.finance_service.entity.DriverWallet
import com.internship.finance_service.repo.DriverWalletRepo
import com.internship.finance_service.utils.WalletValidationManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommandWalletTransferService @Autowired constructor(
    private val walletRepo: DriverWalletRepo,
    private var walletValidatorManager: WalletValidationManager,
    private var walletMapper: WalletMapper
) {
    @Transactional
    fun createWallet(walletDto: WalletDto): WalletDto {
        walletValidatorManager.validateDriverIdUniqueness(walletDto.driverId)
        val wallet = walletMapper.toEntity(walletDto)
        return walletMapper.toDto(walletRepo.save(wallet))
    }
    @Transactional
    fun updateWallet(walletDto: WalletDto): WalletDto {
        val wallet: DriverWallet = walletValidatorManager.getWalletIfExists(walletDto.id)
        walletMapper.updateEntity(wallet, walletDto)
        return walletMapper.toDto(wallet)
    }
    @Transactional
    fun deleteWallet(id:Long){
        walletValidatorManager.getWalletIfExists(id)
        walletRepo.deleteById(id)
    }
}