package com.internship.finance_service.service.card

import com.internship.finance_service.dto.CardDto
import com.internship.finance_service.dto.mapper.CardMapper
import com.internship.finance_service.entity.Card
import com.internship.finance_service.repo.CardRepo
import com.internship.finance_service.utils.CardValidationManager
import org.springframework.beans.factory.annotation.Autowired
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