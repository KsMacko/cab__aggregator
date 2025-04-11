package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.WalletTransferDto
import com.internship.finance_service.entity.WalletTransfer
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
interface WalletTransferMapper : AbstractMapper<WalletTransfer, WalletTransferDto> {
    override fun toDto(entity: WalletTransfer): WalletTransferDto {
        val financialOperation = entity.financialOperation
        val driverWallet = entity.wallet

        return WalletTransferDto(
            id = entity.id,
            driverId = driverWallet?.driverId,
            date = financialOperation?.createdAt,
            amount = financialOperation?.amount,
            remainingAmount = driverWallet?.balance?.minus(financialOperation?.amount ?: BigDecimal.ZERO)
        )
    }
}