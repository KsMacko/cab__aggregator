package com.internship.finance_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "driver_wallets")
class DriverWallet {
    @Id
    val id: Long? = null
    var balance: BigDecimal = BigDecimal.ZERO
    var driverId: Long? = null

    @OneToMany(mappedBy = "wallet")
    var transfers: List<WalletTransfer>? = null

    companion object Fields {
        const val ID = "id"
        const val BALANCE = "balance"
        const val DRIVER_ID = "driverId"
    }
}