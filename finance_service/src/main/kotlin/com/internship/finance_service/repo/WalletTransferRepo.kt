package com.internship.finance_service.repo

import com.internship.finance_service.entity.WalletTransfer
import org.springframework.data.jpa.repository.JpaRepository

interface WalletTransferRepo: JpaRepository<WalletTransfer, Long> {
}