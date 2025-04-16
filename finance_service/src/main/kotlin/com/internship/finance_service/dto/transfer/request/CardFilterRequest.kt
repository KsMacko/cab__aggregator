package com.internship.finance_service.dto.transfer.request

import com.internship.finance_service.enums.CardType
import com.internship.finance_service.enums.OwnerType
import com.internship.finance_service.enums.sort.CardSortFields
import com.internship.finance_service.enums.sort.OrderDirection

data class CardFilterRequest(
    val ownerType: OwnerType? = null,
    val cardType: CardType? = null,
    val ownerId: Long? = null,
    val page: Int = 0,
    val size: Int = 10,
    val orderDirection: OrderDirection = OrderDirection.DESC,
    val sortBy: CardSortFields = CardSortFields.OWNER_TYPE
)