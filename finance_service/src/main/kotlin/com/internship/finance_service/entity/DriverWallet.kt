package com.internship.finance_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.math.BigDecimal

@Entity
@Table(name = "driver_wallets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class DriverWallet {
    @Id
    private val id: Long? = null
    private var balance: BigDecimal = BigDecimal.ZERO

    @OneToMany(mappedBy = "wallet")
    private var transfers: List<WalletTransfer>? = null
}