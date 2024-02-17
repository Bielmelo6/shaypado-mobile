package com.ufape.shaypado.ui.domain.use_case

import com.ufape.shaypado.R

fun validatePhone(phone: String): ValidationResult {
    if (phone.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_phone_required)
    }
    if (!phone.matches(ValidatorUtils.PHONE_REGEX.toRegex())) {
        return ValidationResult(false, R.string.input_validation_invalid_phone)
    }
    return ValidationResult(true)
}

fun validateSpecialties(phone: String): ValidationResult {
    if (phone.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_specialties_required)
    }
    return ValidationResult(true)
}

fun validateAge(phone: String): ValidationResult {
    if (phone.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_age_required)
    }

    if (!phone.matches(ValidatorUtils.IS_NUMERIC_REGEX.toRegex())) {
        return ValidationResult(false, R.string.input_validation_age_numeric)
    }
    return ValidationResult(true)
}

fun validateState(phone: String): ValidationResult {
    if (phone.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_state_required)
    }
    return ValidationResult(true)
}

fun validateCity(phone: String): ValidationResult {
    if (phone.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_city_required)
    }
    return ValidationResult(true)
}

fun validatePlansDocument(file : String? ) : ValidationResult {
    if (file.isNullOrEmpty()) {
        return ValidationResult(false, R.string.input_validation_plans_document_required)
    }
    return ValidationResult(true)
}