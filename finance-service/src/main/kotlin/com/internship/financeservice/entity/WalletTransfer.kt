package com.internship.financeservice.entity


import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "wallet_transfer")
class WalletTransfer {
    @Id
    val id: Long? = null
    lateinit var remainingAmount: BigDecimal

    @ManyToOne
    @JoinColumn(name = "driver_wallet_id")
    lateinit var wallet: DriverWallet

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "financial_operation_id")
    @MapsId
    lateinit var financialOperation: FinancialOperation

    companion object Fields {
        const val ID = "id"
        const val WALLET = "wallet"
        const val FINANCIAL_OPERATION = "financialOperation"
    }
}
