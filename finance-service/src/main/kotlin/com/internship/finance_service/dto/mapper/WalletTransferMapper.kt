package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.WalletTransferDto
import com.internship.finance_service.entity.WalletTransfer
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants
import org.mapstruct.Mappings
import java.math.BigDecimal


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface WalletTransferMapper {
    @Mappings(
        Mapping(target = "date", source = "financialOperation.createdAt"),
        Mapping(target = "amount", source = "financialOperation.amount"),
        Mapping(target = "driverId", source = "wallet.driverId")
    )
    fun toDto(entity: WalletTransfer): WalletTransferDto

    @Mapping(target = WalletTransfer.ID, ignore = true)
    @Mapping(target = WalletTransfer.WALLET, ignore = true)
    @Mapping(target = WalletTransfer.FINANCIAL_OPERATION, ignore = true)
    @Mapping(target = "remainingAmount", ignore = true)
    fun toEntity(dto: WalletTransferDto): WalletTransfer
}