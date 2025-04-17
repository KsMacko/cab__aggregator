package com.internship.financeservice.service

import com.internship.financeservice.dto.transfer.request.CardFilterRequest
import com.internship.financeservice.enums.sort.OrderDirection
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

abstract class PageableObjectCreator {
    fun createPageableObjectByAllFields(
        page: Int,
        size: Int,
        sortByField: String,
        orderDirection: OrderDirection
    ): Pageable {
        val sort = Sort.by(
            Sort.Direction.fromString(orderDirection.toString()),
            sortByField
        )
        return PageRequest.of(page, size, sort)
    }
    fun createPageableObjectByAllFields(
        page: Int,
        size: Int
    ): Pageable {
        return PageRequest.of(page, size)
    }
    fun createPageableObjectByFilter(filter: CardFilterRequest): Pageable {
        val sort = Sort.by(
            if (filter.orderDirection == OrderDirection.ASC) Sort.Direction.ASC else Sort.Direction.DESC,
            filter.sortBy.field
        )
        return PageRequest.of(filter.page, filter.size, sort)
    }
}