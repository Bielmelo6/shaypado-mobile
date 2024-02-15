package com.ufape.shaypado.ui.domain.use_case

import android.util.Patterns
import com.ufape.shaypado.R

fun validateEmail(email: String): ValidationResult {
    if (email.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_email_required)
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return ValidationResult(false, R.string.input_validation_invalid_email)
    }
    return ValidationResult(true)
}

fun validateEmailConfirmation(email: String, emailConfirmation: String): ValidationResult {
    if (emailConfirmation.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_email_confirmation_required)
    }
    if (email != emailConfirmation) {
        return ValidationResult(false, R.string.input_validation_email_confirmation_invalid)
    }
    return ValidationResult(true)
}

fun validatePassword(password: String): ValidationResult {
    if (password.length < 8) {
        return ValidationResult(false,  R.string.input_validation_password_wrong_length)
    }

    if (!password.matches(ValidatorUtils.STRONG_PASSWORD_REGEX)) {
        return ValidationResult(false,  R.string.input_validation_strong_password_required)
    }
    return ValidationResult(true)
}

fun validatePasswordConfirmation(password: String, passwordConfirmation: String): ValidationResult {
    if (passwordConfirmation.isEmpty()) {
        return ValidationResult(false,  R.string.input_validation_password_confirmation_required)
    }
    if (password != passwordConfirmation) {
        return ValidationResult(false, R.string.input_validation_password_confirmation_invalid)
    }
    return ValidationResult(true)
}

fun validateName(name: String): ValidationResult {
    if (name.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_user_name_required)
    }
    return ValidationResult(true)
}

fun validateTermsAndConditions(termsAndConditions: Boolean): ValidationResult {
    if (!termsAndConditions) {
        return ValidationResult(false, R.string.input_validation_terms_and_conditions_required)
    }
    return ValidationResult(true)
}