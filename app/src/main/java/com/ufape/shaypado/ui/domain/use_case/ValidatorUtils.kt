package com.ufape.shaypado.ui.domain.use_case

import androidx.annotation.StringRes

data class ValidationResult(
    val isValid: Boolean,
    @StringRes val error: Int? = null
)

fun hasError(vararg validations: ValidationResult): Boolean {
    return validations.any { !it.isValid }
}


