package com.ufape.shaypado.ui.screens.trainer.createTrainings

import androidx.annotation.StringRes

data class TrainingsFormState(
    val name: String = "",
    @StringRes val nameError: Int? = null,
    val category: String = "",
    @StringRes val categoryError: Int? = null,
    val exercises: List<ExerciseDisplayState> = emptyList(),
)

data class ExerciseDisplayState(
    val id : String = "",
    val name: String = "",
    val description: String = "",
)

sealed class TrainingsFormEvent {
    data class OnNameChanged(val name: String) : TrainingsFormEvent()
    data class OnCategoryChanged(val category: String) : TrainingsFormEvent()
    data class OnExercisesChanged(val exercises: List<ExerciseDisplayState>) : TrainingsFormEvent()
    data object RemoveCurrentTraining : TrainingsFormEvent()
    data object OnSubmit : TrainingsFormEvent()
}