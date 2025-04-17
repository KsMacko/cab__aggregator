package com.internship.finance_service.service.specification

import com.internship.finance_service.dto.transfer.request.WalletTransferFilterRequest
import com.internship.finance_service.entity.DriverWallet
import com.internship.finance_service.entity.FinancialOperation
import com.internship.finance_service.entity.WalletTransfer
import jakarta.persistence.criteria.JoinType
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

class WalletTransferSpecification : AbstractSpecification() {
    companion object {
        fun createFilterSpecification(filter: WalletTransferFilterRequest): Specification<WalletTransfer> {
            return Specification { root, _, criteriaBuilder ->
                val predicates = mutableListOf<Predicate>()
                val driverWalletJoin = root.join<WalletTransfer, DriverWallet>(WalletTransfer.WALLET, JoinType.INNER)
                addPredicateIfNotNull(
                    predicates,
                    criteriaBuilder,
                    root.join<WalletTransfer, FinancialOperation>(WalletTransfer.FINANCIAL_OPERATION, JoinType.INNER),
                    FinancialOperation.CREATED_AT,
                    filter.createdAt
                )
                addPredicateIfNotNull(
                    predicates,
                    criteriaBuilder,
                    driverWalletJoin,
                    DriverWallet.DRIVER_ID,
                    filter.driverId
                )

                criteriaBuilder.and(*predicates.toTypedArray())
            }
        }
    }
}