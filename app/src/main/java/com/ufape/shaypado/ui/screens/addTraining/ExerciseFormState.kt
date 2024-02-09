package com.ufape.shaypado.ui.screens.addTraining

import androidx.annotation.StringRes

data class ExerciseFormState(
    val name: String = "",
    @StringRes val nameError: Int? = null,

    val series: Int = 1,
    @StringRes val seriesError: Int? = null,

    val repetitions: Int = 1,
    @StringRes val repetitionsError: Int? = null,

    val calories: Int = 0,
    @StringRes val caloriesError: Int? = null,
)
