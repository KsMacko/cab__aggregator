package com.internship.financeservice.dto.transfer.request

import com.internship.financeservice.enums.CardType
import com.internship.financeservice.enums.OwnerType
import com.internship.financeservice.enums.sort.CardSortFields
import com.internship.financeservice.enums.sort.OrderDirection

data class CardFilterRequest(
    val ownerType: OwnerType? = null,
    val cardType: CardType? = null,
    val ownerId: Long? = null,
    val page: Int = 0,
    val size: Int = 10,
    val orderDirection: OrderDirection = OrderDirection.DESC,
    val sortBy: CardSortFields = CardSortFields.OWNER_TYPE
)