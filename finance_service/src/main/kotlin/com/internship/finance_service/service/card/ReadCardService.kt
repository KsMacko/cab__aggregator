package com.internship.finance_service.service.card

import com.internship.finance_service.dto.CardDto
import com.internship.finance_service.dto.mapper.CardMapper
import com.internship.finance_service.dto.transfer.request.CardFilterRequest
import com.internship.finance_service.dto.transfer.response.CardPackageDto
import com.internship.finance_service.entity.Card
import com.internship.finance_service.enums.sort.OrderDirection
import com.internship.finance_service.repo.CardRepo
import com.internship.finance_service.service.PageableObjectCreator
import com.internship.finance_service.service.specification.CardSpecification
import com.internship.finance_service.utils.CardValidationManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadCardService @Autowired constructor(
    private var cardRepo: CardRepo,
    private var cardValidationManager: CardValidationManager,
    private var cardMapper: CardMapper
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
    fun findCardById(id: Long): CardDto {
        return cardMapper.toDto(cardValidationManager.getCardIfExists(id))
    }
}