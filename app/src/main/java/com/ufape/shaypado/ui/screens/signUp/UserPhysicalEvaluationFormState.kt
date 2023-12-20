package com.ufape.shaypado.ui.screens.signUp

data class UserPhysicalEvaluationFormState(
    val weight: String = "",
    val weightError: String? = null,
    val height: String = "",
    val heightError: String? = null,
    val objective: String = "",
    val objectiveError: String? = null,
    val workoutFrequency: String = "",
    val workoutFrequencyError: String? = null,
    val anyDisease: Boolean = false,
    val anyDiseaseError: String? = null,
)
