package com.internship.finance_service.entity


import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "wallet_transfer")
class WalletTransfer{
    @Id
    val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "driver_wallet_id")
    var wallet: DriverWallet? = null

    @OneToOne
    @JoinColumn(name = "financial_operation_id")
    @MapsId
    var financialOperation: FinancialOperation? = null
    companion object Fields {
        const val ID = "id"
        const val WALLET = "wallet"
        const val FINANCIAL_OPERATION = "financialOperation"
    }
}
