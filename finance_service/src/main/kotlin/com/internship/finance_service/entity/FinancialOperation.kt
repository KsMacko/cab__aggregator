package com.internship.finance_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "finance_operation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class FinancialOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
    @CreationTimestamp
    private var createdAt: LocalDateTime? = null
    private var amount: BigDecimal? = null

    @OneToOne(mappedBy = "financialOperation")
    private var walletTransfer: WalletTransfer? = null

    @OneToOne(mappedBy = "financialOperation")
    private var payment: Payment? = null

    @ManyToOne
    @JoinColumn(name = "card_id")
    private var card: Card? = null
}