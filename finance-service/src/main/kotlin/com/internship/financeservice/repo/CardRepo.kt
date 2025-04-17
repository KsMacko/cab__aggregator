package com.internship.financeservice.repo

import com.internship.financeservice.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CardRepo : JpaRepository<Card, Long>, JpaSpecificationExecutor<Card> {
    fun existsCardByOwnerId(ownerId: Long): Boolean
}