package com.internship.financeservice.utils.exceptions.transfer

data class BaseValidationException(val message: String, val errors: List<String>)