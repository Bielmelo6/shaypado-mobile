package com.ufape.shaypado.ui.domain.use_case

import com.ufape.shaypado.R

fun validateWeight(weight: String): ValidationResult {
    if (weight.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_weight_required)
    }
    //check if weight is a number
    if (!weight.matches("-?\\d+(\\.\\d+)?".toRegex())) {
        return ValidationResult(false, R.string.input_validation_weight_numeric)
    }
    return ValidationResult(true)
}

fun validateHeight(height: String): ValidationResult {
    if (height.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_height_required)
    }
    //check if height is a number
    if (!height.matches("-?\\d+(\\.\\d+)?".toRegex())) {
        return ValidationResult(false, R.string.input_validation_height_numeric)
    }
    return ValidationResult(true)
}

fun validateWorkoutFrequency(workoutFrequency: String): ValidationResult {
    if (workoutFrequency.isEmpty()) {
        return ValidationResult(false, R.string.input_validation_workout_per_week_required)
    }
    //check if workout frequency is a number
    if (!workoutFrequency.matches("-?\\d+(\\.\\d+)?".toRegex())) {
        return ValidationResult(false, R.string.input_validation_workout_per_week_numeric)
    }
    return ValidationResult(true)
}

