package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.WalletTransferDto
import com.internship.finance_service.entity.WalletTransfer
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import java.math.BigDecimal


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface WalletTransferMapper {
    fun toDto(entity: WalletTransfer): WalletTransferDto {
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

    @Mapping(target = WalletTransfer.ID, ignore = true)
    @Mapping(target = WalletTransfer.WALLET, ignore = true)
    @Mapping(target = WalletTransfer.FINANCIAL_OPERATION, ignore = true)
    fun toEntity(dto: WalletTransferDto): WalletTransfer
}