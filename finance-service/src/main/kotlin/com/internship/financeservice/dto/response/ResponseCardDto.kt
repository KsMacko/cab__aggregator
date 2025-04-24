package com.internship.financeservice.dto.response

import com.internship.financeservice.enums.CardType
import com.internship.financeservice.enums.OwnerType
import java.time.LocalDate

data class ResponseCardDto(
    val id: Long,
    val lastFourDigits: String,
    val expirationDate: LocalDate,
    val owner: OwnerType,
    val ownerId: Long,
    val cardType: CardType,
)