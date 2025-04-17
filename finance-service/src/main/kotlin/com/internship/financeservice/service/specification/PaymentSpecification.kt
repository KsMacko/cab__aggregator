package com.internship.financeservice.service.specification

import com.internship.financeservice.dto.transfer.request.PaymentFilterRequest
import com.internship.financeservice.entity.FinancialOperation
import com.internship.financeservice.entity.Payment
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

class PaymentSpecification : AbstractSpecification() {
    companion object {
        fun createFilterSpecification(filter: PaymentFilterRequest): Specification<Payment> {
            return Specification { root, _, criteriaBuilder ->
                val predicates = mutableListOf<Predicate>()

                addPredicateIfNotNull(
                    predicates,
                    criteriaBuilder,
                    root.join<Payment, FinancialOperation>(Payment.FINANCIAL_OPERATION),
                    FinancialOperation.CREATED_AT,
                    filter.createdAt
                )
                addPredicateIfNotNull(predicates, criteriaBuilder, root, Payment.PASSENGER_ID, filter.passengerId)
                addPredicateIfNotNull(predicates, criteriaBuilder, root, Payment.PAYMENT_TYPE, filter.paymentType)

                criteriaBuilder.and(*predicates.toTypedArray())
            }
        }
    }
}