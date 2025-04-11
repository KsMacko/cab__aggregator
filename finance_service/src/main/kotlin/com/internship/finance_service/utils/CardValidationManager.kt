package com.internship.finance_service.utils

import com.internship.finance_service.entity.Card
import com.internship.finance_service.repo.CardRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CardValidationManager @Autowired constructor(
    private val cardRepo: CardRepo
){
    fun getCardIfExists(id: Long?): Card {
        id?:
            throw RuntimeException("card.id.notNull")
        return cardRepo.findById(id).orElseThrow { RuntimeException("card.notFound") }
    }
    fun checkCardIfExists(id: Long) {
        if(!cardRepo.existsById(id))
            throw RuntimeException("card.id.notFound")
    }
    fun validateCardExistsForOwner(ownerId: Long?) {
        ownerId?:
            throw RuntimeException("owner.id.notNull")
        if (!cardRepo.existsCardByOwnerId(ownerId)) {
            throw RuntimeException("card.id.notFound")
        }
    }
}