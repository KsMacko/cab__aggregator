package com.internship.finance_service.repo

import com.internship.finance_service.entity.WalletTransfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface WalletTransferRepo : JpaRepository<WalletTransfer, Long>, JpaSpecificationExecutor<WalletTransfer> {
}