package com.internship.financeservice.dto.mapper

import com.internship.financeservice.dto.WalletDto
import com.internship.financeservice.entity.DriverWallet
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface WalletMapper {
    fun toDto(entity: DriverWallet): WalletDto

    @Mapping(target = DriverWallet.ID, ignore = true)
    @Mapping(target = DriverWallet.TRANSFERS, ignore = true)
    fun toEntity(dto: WalletDto): DriverWallet
}