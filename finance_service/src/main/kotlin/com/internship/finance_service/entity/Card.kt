package com.internship.finance_service.entity


import com.internship.finance_service.enums.CardType
import com.internship.finance_service.enums.OwnerType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDate

@Entity
@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
    private var lastFourDigits: String? = null
    private var expirationDate: LocalDate? = null
    private var owner: OwnerType? = null
    private var ownerId: Long? = null

    @Enumerated(EnumType.STRING)
    private var cardType: CardType? = null

    @OneToMany(mappedBy = "card")
    private var financialOperation:List<FinancialOperation>? = null
}