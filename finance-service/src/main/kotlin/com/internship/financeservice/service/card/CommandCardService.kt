package com.internship.financeservice.service.card

import com.internship.financeservice.dto.response.ResponseCardDto
import com.internship.financeservice.dto.mapper.CardMapper
import com.internship.financeservice.dto.request.RequestCardDto
import com.internship.financeservice.repo.CardRepo
import com.internship.financeservice.utils.validation.CardValidationManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommandCardService(
    private val cardRepo: CardRepo,
    private val cardValidationManager: CardValidationManager,
    private val cardMapper: CardMapper
) {
    @Transactional
    fun createCard(requestCardDto: RequestCardDto): ResponseCardDto =
        cardMapper.toDto(cardRepo.save(cardMapper.toEntity(requestCardDto)))

    @Transactional
    fun deleteCard(id: Long) {
        cardValidationManager.checkCardIfExists(id)
        cardRepo.deleteById(id)
    }
}