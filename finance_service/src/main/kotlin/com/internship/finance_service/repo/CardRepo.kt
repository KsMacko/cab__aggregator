package com.internship.finance_service.repo

import com.internship.finance_service.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CardRepo : JpaRepository<Card, Long>, JpaSpecificationExecutor<Card> {
    fun existsCardByOwnerId(ownerId: Long): Boolean
}