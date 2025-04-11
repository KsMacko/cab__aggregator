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
class CommandCardService @Autowired constructor(
    private var cardRepo: CardRepo,
    private var cardValidationManager: CardValidationManager,
    private var cardMapper: CardMapper
){
    @Transactional
    fun createCard(cardDto: CardDto):CardDto{
        return cardMapper.toDto(cardRepo.save(cardMapper.toEntity(cardDto)))
    }
    @Transactional
    fun updateCard(cardDto: CardDto):CardDto{
        val card: Card = cardValidationManager.getCardIfExists(cardDto.id)
        cardMapper.updateEntity(card, cardDto)
        return cardMapper.toDto(card)
    }
    @Transactional
    fun deleteCard(id:Long){
        cardValidationManager.checkCardIfExists(id)
        cardRepo.deleteById(id)
    }
}