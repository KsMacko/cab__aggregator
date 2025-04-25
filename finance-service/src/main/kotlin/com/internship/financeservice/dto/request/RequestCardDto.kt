package com.internship.financeservice.dto.request

import com.internship.financeservice.enums.CardType
import com.internship.financeservice.enums.OwnerType
import java.time.LocalDate

data class RequestCardDto (
    val lastFourDigits: String,
    val expirationDate: LocalDate,
    val owner: OwnerType,
    val ownerId: Long,
    val cardType: CardType,
)