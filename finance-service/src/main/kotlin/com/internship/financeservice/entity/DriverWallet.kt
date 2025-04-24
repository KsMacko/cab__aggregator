package com.internship.financeservice.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "driver_wallets")
class DriverWallet (){
    constructor(driverId: Long): this(){
        this.driverId = driverId
        this.balance = BigDecimal.ZERO
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    var balance: BigDecimal = BigDecimal.ZERO
    var driverId: Long = 0

    @OneToMany(mappedBy = "wallet", cascade = [CascadeType.ALL])
    var transfers: List<WalletTransfer>? = null

    companion object Fields {
        const val ID = "id"
        const val BALANCE = "balance"
        const val DRIVER_ID = "driverId"
        const val TRANSFERS = "transfers"
    }
}