package com.internship.finance_service.entity

import com.internship.finance_service.enums.PaymentType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "payment")
class Payment {
    @Id
    val id: Long? = null
    var passengerId : Long? = null
    var paymentType : PaymentType? = null

    @OneToOne
    @MapsId
    var financialOperation: FinancialOperation? = null
    companion object Fields {
        const val ID = "id"
        const val PASSENGER_ID = "passengerId"
        const val PAYMENT_TYPE = "paymentType"
        const val FINANCIAL_OPERATION = "financialOperation"
    }
}