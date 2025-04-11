package com.internship.finance_service.dto.mapper

import com.internship.finance_service.dto.CardDto
import com.internship.finance_service.entity.Card
import org.springframework.stereotype.Component

@Component
interface CardMapper: AbstractMapper<Card, CardDto> {}