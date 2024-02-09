package com.ufape.shaypado.ui.screens.addTraining

import androidx.annotation.StringRes

data class TrainingFormState(
    val name: String = "",
    @StringRes val nameError: Int? = null,

    val daysOfWeek: List<Boolean> = listOf(false, false, false, false, false, false, false),
    @StringRes val daysOfWeekError: Int? = null,

    val trainingStartTime: String = "0",
    @StringRes val trainingStartTimeError: Int? = null,

    val trainingEndTime: String = "0",
    @StringRes val trainingEndTimeError: Int? = null,

    val trainingType: String = "",
    @StringRes val trainingTypeError: Int? = null,

    val exercises: List<String> = listOf(),
    @StringRes val exercisesError: Int? = null,
)
