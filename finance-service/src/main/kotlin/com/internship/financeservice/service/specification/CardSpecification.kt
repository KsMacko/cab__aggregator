package com.internship.financeservice.service.specification

import com.internship.financeservice.dto.transfer.request.CardFilterRequest
import com.internship.financeservice.entity.Card
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification

class CardSpecification : AbstractSpecification() {
    companion object {
        fun createFilterSpecification(filter: CardFilterRequest): Specification<Card> {
            return Specification { root, _, criteriaBuilder ->
                val predicates = mutableListOf<Predicate>()

                addPredicateIfNotNull(predicates, criteriaBuilder, root, Card.OWNER, filter.ownerType)
                addPredicateIfNotNull(predicates, criteriaBuilder, root, Card.CARD_TYPE, filter.cardType)
                addPredicateIfNotNull(predicates, criteriaBuilder, root, Card.OWNER_ID, filter.ownerId)

                criteriaBuilder.and(*predicates.toTypedArray())
            }
        }
    }
}