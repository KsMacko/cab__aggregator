package com.internship.financeservice.utils.exceptions

enum class ExceptionCodes(private val code: String) {
    ERROR_NOT_READABLE("error.notReadable"),
    ERROR_INVALID_INPUT("error.invalidInput"),
    WALLET_NOT_FOUND("wallet.notFound"),
    CARD_NOT_FOUND("card.notFound"),
    PAYMENT_NOT_FOUND("payment.notFound"),
    WALLET_TRANSFER_NOT_FOUND("walletTransfer.notFound"),
    WALLET_TRANSFER_INVALID_AMOUNT("wallet.transfer.invalidAmount");

    fun getCode(): String {
        return code
    }
}