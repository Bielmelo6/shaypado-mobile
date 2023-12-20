package com.ufape.shaypado.ui.domain.use_case

import android.util.Patterns

fun validateEmail(email: String): ValidationResult {
    if (email.isEmpty()) {
        return ValidationResult(false, "Email is required")
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return ValidationResult(false, "Invalid email")
    }
    return ValidationResult(true)
}

fun validateEmailConfirmation(email: String, emailConfirmation: String): ValidationResult {
    if (email != emailConfirmation) {
        return ValidationResult(false, "Emails don't match")
    }
    return ValidationResult(true)
}

fun validatePassword(password: String): ValidationResult {
    if (password.length < 8) {
        return ValidationResult(false, "Password must be at least 6 characters")
    }
    return ValidationResult(true)
}

fun validatePasswordConfirmation(password: String, passwordConfirmation: String): ValidationResult {
    if (password != passwordConfirmation) {
        return ValidationResult(false, "Passwords don't match")
    }
    return ValidationResult(true)
}

fun validateName(name: String): ValidationResult {
    if (name.isEmpty()) {
        return ValidationResult(false, "Name is required")
    }
    return ValidationResult(true)
}

fun validateWorkoutType(workoutType: String): ValidationResult {
    if (workoutType.isEmpty()) {
        return ValidationResult(false, "Workout type is required")
    }
    return ValidationResult(true)
}

fun validateUserType(userType: String): ValidationResult {
    if (userType.isEmpty()) {
        return ValidationResult(false, "User type is required")
    }
    return ValidationResult(true)
}