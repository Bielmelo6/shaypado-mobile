package com.ufape.shaypado.ui.domain.use_case

fun validateWeight(weight: String): ValidationResult {
    if (weight.isEmpty()) {
        return ValidationResult(false, "Weight is required")
    }
    //check if weight is a number
    if (!weight.matches("-?\\d+(\\.\\d+)?".toRegex())) {
        return ValidationResult(false, "Weight must be a number")
    }
    return ValidationResult(true)
}

fun validateHeight(height: String): ValidationResult {
    if (height.isEmpty()) {
        return ValidationResult(false, "Height is required")
    }
    //check if height is a number
    if (!height.matches("-?\\d+(\\.\\d+)?".toRegex())) {
        return ValidationResult(false, "Height must be a number")
    }
    return ValidationResult(true)
}

fun validateWorkoutFrequency(workoutFrequency: String): ValidationResult {
    if (workoutFrequency.isEmpty()) {
        return ValidationResult(false, "Workout frequency is required")
    }
    //check if workout frequency is a number
    if (!workoutFrequency.matches("-?\\d+(\\.\\d+)?".toRegex())) {
        return ValidationResult(false, "Workout frequency must be a number")
    }
    return ValidationResult(true)
}

