package com.internship.financeservice.service

import com.internship.commonevents.event.ConfirmedPaymentRequest
import com.internship.financeservice.service.finance.CommandFinanceOperationService
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.JmsListener

@Configuration
class ArtemisConsumer (
    private val commandFinanceOperationService: CommandFinanceOperationService
){

    @JmsListener(destination = "payment-confirmation")
    fun receiveCashPaymentConfirmation(event: ConfirmedPaymentRequest) {
        try {
            println("recieved " + event)
            commandFinanceOperationService.createPaymentByCash(event)
        } catch (e: Exception) {
            throw RuntimeException("Failed to deserialize message", e)
        }
    }

}