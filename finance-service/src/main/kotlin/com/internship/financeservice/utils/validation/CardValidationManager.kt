package com.internship.financeservice.utils.validation

import com.internship.financeservice.entity.Card
import com.internship.financeservice.repo.CardRepo
import com.internship.financeservice.utils.exceptions.ExceptionCodes
import com.internship.financeservice.utils.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CardValidationManager @Autowired constructor(
    private val cardRepo: CardRepo
) {
    fun getCardIfExists(id: Long): Card {
        return cardRepo.findById(id).orElseThrow {
            ResourceNotFoundException(ExceptionCodes.CARD_NOT_FOUND.getCode())
        }
    }

    fun checkCardIfExists(id: Long) {
        if (!cardRepo.existsById(id))
            throw ResourceNotFoundException(ExceptionCodes.CARD_NOT_FOUND.getCode())
    }

    fun validateCardExistsForOwner(ownerId: Long) {
        if (!cardRepo.existsCardByOwnerId(ownerId)) {
            throw ResourceNotFoundException(ExceptionCodes.CARD_NOT_FOUND.getCode())
        }
    }
}