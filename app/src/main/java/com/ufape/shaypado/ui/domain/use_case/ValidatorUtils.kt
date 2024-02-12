package com.ufape.shaypado.ui.domain.use_case

import androidx.annotation.StringRes

data class ValidationResult(
    val isValid: Boolean,
    @StringRes val error: Int? = null
)

fun hasError(vararg validations: ValidationResult): Boolean {
    return validations.any { !it.isValid }
}

class ValidatorUtils {
    companion object {
        val STRONG_PASSWORD_REGEX = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>?])(?!.+(0123|1234|2345|3456|4567|5678|6789)).+$")
    }
}

