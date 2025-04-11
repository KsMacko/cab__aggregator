package com.internship.finance_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "finance_operation")
class FinancialOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    @CreationTimestamp
    val createdAt: LocalDateTime? = null
    var amount: BigDecimal? = null

    @OneToOne(mappedBy = "financialOperation")
    var walletTransfer: WalletTransfer? = null

    @OneToOne(mappedBy = "financialOperation")
    var payment: Payment? = null

    @ManyToOne
    @JoinColumn(name = "card_id")
    var card: Card? = null

    companion object Fields {
        const val ID = "id"
        const val CREATED_AT = "createdAt"
        const val AMOUNT = "amount"
    }
}