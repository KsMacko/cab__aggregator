package com.internship.finance_service.dto

import com.internship.finance_service.enums.CardType
import com.internship.finance_service.enums.OwnerType
import java.time.LocalDate

data class CardDto (
    val id: Long? = null,
    val lastFourDigits: String? = null,
    val expirationDate: LocalDate? = null,
    val owner: OwnerType? = null,
    val ownerId: Long? = null,
    val cardType: CardType? = null,
)