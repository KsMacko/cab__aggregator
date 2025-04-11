package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.WalletDto
import com.internship.finance_service.entity.DriverWallet
import org.springframework.stereotype.Component

@Component
interface WalletMapper: AbstractMapper<DriverWallet, WalletDto> {
}