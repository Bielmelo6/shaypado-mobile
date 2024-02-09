package com.ufape.shaypado.ui.screens.signUp

import androidx.annotation.StringRes

data class UserPhysicalEvaluationFormState(
    val weight: String = "",
    @StringRes val weightError: Int? = null,
    val height: String = "",
    @StringRes val heightError: Int? = null,
    val objective: String = "",
    @StringRes  val objectiveError: Int? = null,
    val workoutFrequency: String = "",
    @StringRes  val workoutFrequencyError: Int? = null,
    val anyDisease: Boolean = false,
    @StringRes val anyDiseaseError: Int? = null,
    val saveCorporalData: Boolean = false,
    @StringRes val saveCorporalDataError: Int? = null,
)
