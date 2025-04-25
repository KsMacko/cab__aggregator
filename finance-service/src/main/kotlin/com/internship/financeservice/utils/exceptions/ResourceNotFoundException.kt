package com.internship.financeservice.utils.exceptions

data class ResourceNotFoundException(override val message : String): RuntimeException(message) {
}