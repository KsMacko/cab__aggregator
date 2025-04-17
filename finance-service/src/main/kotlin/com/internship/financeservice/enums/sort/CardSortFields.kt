package com.internship.financeservice.enums.sort

import com.internship.financeservice.entity.Card

enum class CardSortFields(val field: String) {
    OWNER_TYPE(Card.OWNER),
    CARD_TYPE(Card.CARD_TYPE);
}