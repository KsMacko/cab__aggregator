package com.internship.finance_service.enums.sort

import com.internship.finance_service.entity.Card

enum class CardSortFields(val field: String) {
    OWNER_TYPE(Card.OWNER),
    CARD_TYPE(Card.CARD_TYPE);
}