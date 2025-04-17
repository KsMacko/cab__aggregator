package com.internship.finance_service.service.specification

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate

abstract class AbstractSpecification {
    companion object {
        internal fun <T> addPredicateIfNotNull(
            predicates: MutableList<Predicate>,
            criteriaBuilder: CriteriaBuilder,
            root: Path<*>,
            fieldName: String,
            value: T?
        ) {
            if (value != null) {
                predicates.add(criteriaBuilder.equal(root.get<T>(fieldName), value))
            }
        }
    }
}