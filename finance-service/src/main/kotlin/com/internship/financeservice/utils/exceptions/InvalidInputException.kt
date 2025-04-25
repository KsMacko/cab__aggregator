package com.internship.financeservice.utils.exceptions

data class InvalidInputException(override val message : String): RuntimeException(message) {
}