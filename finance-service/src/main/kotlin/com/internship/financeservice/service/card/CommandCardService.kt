package com.internship.financeservice.service.card

import com.internship.financeservice.dto.CardDto
import com.internship.financeservice.dto.mapper.CardMapper
import com.internship.financeservice.repo.CardRepo
import com.internship.financeservice.utils.CardValidationManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommandCardService(
    private val cardRepo: CardRepo,
    private val cardValidationManager: CardValidationManager,
    private val cardMapper: CardMapper
) {
    @Transactional
    fun createCard(cardDto: CardDto): CardDto =
        cardMapper.toDto(cardRepo.save(cardMapper.toEntity(cardDto)))

    @Transactional
    fun deleteCard(id: Long) {
        cardValidationManager.checkCardIfExists(id)
        cardRepo.deleteById(id)
    }
}