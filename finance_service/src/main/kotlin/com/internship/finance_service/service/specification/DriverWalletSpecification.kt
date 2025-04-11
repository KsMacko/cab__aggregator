package com.internship.finance_service.service.specification

import com.internship.finance_service.dto.transfer.request.WalletFilterRequest
import com.internship.finance_service.entity.DriverWallet
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

class DriverWalletSpecification : AbstractSpecification() {

    companion object {
        fun createFilterSpecification(filter: WalletFilterRequest): Specification<DriverWallet> {
            return Specification { root, _, criteriaBuilder ->
                val predicates = mutableListOf<Predicate>()
                addPredicateIfNotNull(predicates, criteriaBuilder, root, DriverWallet.DRIVER_ID, filter.driverId)
                filter.minBalance?.let {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(DriverWallet.BALANCE), it))
                }
                filter.maxBalance?.let {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(DriverWallet.BALANCE), it))
                }

                criteriaBuilder.and(*predicates.toTypedArray())
            }
        }
    }
}