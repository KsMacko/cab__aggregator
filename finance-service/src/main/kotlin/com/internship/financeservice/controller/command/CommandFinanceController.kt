package com.internship.financeservice.controller.command

import com.internship.commonevents.event.ConfirmedPaymentRequest
import com.internship.financeservice.dto.request.RequestWalletTransferDto
import com.internship.financeservice.dto.response.ResponsePaymentDto
import com.internship.financeservice.dto.response.ResponseTransferDto
import com.internship.financeservice.service.finance.CommandFinanceOperationService
import com.internship.financeservice.service.wallet.WalletService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("api/v1/finance")
class CommandFinanceController (
    private val commandFinanceOperationService: CommandFinanceOperationService,
    private val walletService: WalletService
){
    @PostMapping("/payment")
    private fun createCardPayment(
        @RequestBody payment: ConfirmedPaymentRequest
    ):ResponsePaymentDto{
        return commandFinanceOperationService.createPaymentByCard(payment)
    }
    @PostMapping("/wallet-transfer")
    private fun createWalletTransfer(
        @RequestBody requestWalletTransferDto: RequestWalletTransferDto
    ):ResponseEntity<ResponseTransferDto>{
        val  walletTransfer = commandFinanceOperationService.createWalletTransfer(requestWalletTransferDto)
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(walletTransfer.id)
            .toUri()
        return ResponseEntity
            .created(location)
            .body(walletTransfer)
    }
    @PostMapping("/wallet/driver/{id}")
    fun createWallet(@PathVariable id:Long): ResponseEntity<Void> {
        walletService.createWallet(id)
        return ResponseEntity.ok().build()
    }
    @DeleteMapping("/wallet/driver/{id}")
    fun deleteWallet(@PathVariable id:Long): ResponseEntity<Void> {
        walletService.deleteWallet(id)
        return ResponseEntity.ok().build()
    }
}