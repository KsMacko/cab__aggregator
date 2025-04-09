package com.internship.finance_service.entity


import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "wallet_transfer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class WalletTransfer{
    @Id
    private val id: Long? = null

    @ManyToOne
    @JoinColumn(name = "driver_wallet_id")
    private var wallet: DriverWallet? = null

    @OneToOne
    @JoinColumn(name = "financial_operation_id")
    @MapsId
    private var financialOperation: FinancialOperation? = null
}
