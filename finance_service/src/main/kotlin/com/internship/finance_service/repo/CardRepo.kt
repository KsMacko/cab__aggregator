package com.internship.finance_service.repo

import com.internship.finance_service.entity.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepo: JpaRepository<Card, Long>{
}