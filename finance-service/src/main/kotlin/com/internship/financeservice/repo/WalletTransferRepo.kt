package com.internship.financeservice.repo

import com.internship.financeservice.entity.WalletTransfer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface WalletTransferRepo : JpaRepository<WalletTransfer, Long>, JpaSpecificationExecutor<WalletTransfer> {
}