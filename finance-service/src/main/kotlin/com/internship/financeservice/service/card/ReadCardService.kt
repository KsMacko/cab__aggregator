package com.internship.financeservice.service.card

import com.internship.financeservice.dto.response.ResponseCardDto
import com.internship.financeservice.dto.mapper.CardMapper
import com.internship.financeservice.dto.transfer.request.CardFilterRequest
import com.internship.financeservice.dto.transfer.response.CardPackageDto
import com.internship.financeservice.entity.Card
import com.internship.financeservice.repo.CardRepo
import com.internship.financeservice.service.PageableObjectCreator
import com.internship.financeservice.service.specification.CardSpecification
import com.internship.financeservice.utils.CardValidationManager
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadCardService(
    private val cardRepo: CardRepo,
    private val cardValidationManager: CardValidationManager,
    private val cardMapper: CardMapper
): PageableObjectCreator(){
    @Transactional(readOnly = true)
    fun findAllCards(filter: CardFilterRequest): CardPackageDto {
        val spec = CardSpecification.createFilterSpecification(filter)

        val pageable = createPageableObjectByFilter(filter)
        val page: Page<Card> = cardRepo.findAll(spec, pageable)
        val cardsDto = page.content.map(cardMapper::toDto)

        return CardPackageDto(
            cards = cardsDto,
            page = page.number,
            size = page.size,
            totalPages = page.totalPages,
            totalElements = page.totalElements
        )
    }

    @Transactional(readOnly = true)
    fun findCardById(id: Long): ResponseCardDto =
        cardMapper.toDto(cardValidationManager.getCardIfExists(id))
}