package com.internship.financeservice.utils

import com.internship.financeservice.entity.Card
import com.internship.financeservice.repo.CardRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CardValidationManager @Autowired constructor(
    private val cardRepo: CardRepo
) {
    fun getCardIfExists(id: Long): Card {
        return cardRepo.findById(id).orElseThrow { RuntimeException("card.notFound") }
    }

    fun checkCardIfExists(id: Long) {
        if (!cardRepo.existsById(id))
            throw RuntimeException("card.id.notFound")
    }

    fun validateCardExistsForOwner(ownerId: Long) {
        if (!cardRepo.existsCardByOwnerId(ownerId)) {
            throw RuntimeException("card.id.notFound")
        }
    }
}