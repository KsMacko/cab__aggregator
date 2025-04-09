package com.internship.finance_service.entity

import com.internship.finance_service.enums.PaymentType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Payment {
    @Id
    private val id: Long? = null
    private var passengerId : Long? = null
    private var paymentType : PaymentType? = null

    @OneToOne
    @MapsId
    private var financialOperation: FinancialOperation? = null
}