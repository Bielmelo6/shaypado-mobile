package com.ufape.shaypado.ui.domain.use_case

data class ValidationResult(
    val isValid: Boolean,
    val error: String? = null
)

fun hasError(vararg validations: ValidationResult): Boolean {
    return validations.any { !it.isValid }
}


