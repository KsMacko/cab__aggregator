package com.internship.finance_service.entity

import com.internship.finance_service.enums.PaymentType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "payment")
class Payment {
    @Id
    val id: Long? = null
    var passengerId: Long = 0
    lateinit var paymentType: PaymentType

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    lateinit var financialOperation: FinancialOperation

    companion object Fields {
        const val ID = "id"
        const val PASSENGER_ID = "passengerId"
        const val PAYMENT_TYPE = "paymentType"
        const val FINANCIAL_OPERATION = "financialOperation"
    }
}