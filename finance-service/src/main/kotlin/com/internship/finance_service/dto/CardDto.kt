package com.internship.finance_service.dto

import com.internship.finance_service.enums.CardType
import com.internship.finance_service.enums.OwnerType
import java.time.LocalDate

data class CardDto(
    val id: Long? = null,
    val lastFourDigits: String,
    val expirationDate: LocalDate,
    val owner: OwnerType,
    val ownerId: Long,
    val cardType: CardType,
)