package com.ufape.shaypado.ui.screens.trainer.createClass

import androidx.annotation.StringRes

data class ClassFormState(
    val name: String = "",
    @StringRes val nameError: Int? = null,
    val daysOfWeek: List<Boolean> = List(7) { false },
    @StringRes val daysOfWeekError: Int? = null,
    val startingTime: String = "00:00",
    @StringRes val startingTimeError: Int? = null,
    val endingTime: String = "00:00",
    @StringRes val endingTimeError: Int? = null,
    val trainings : List<String> = emptyList(),
    @StringRes val trainingsError: Int? = null,
    val students : List<String> = emptyList(),
    @StringRes val studentsError: Int? = null,
)


sealed class ClassFormEvent {
    data class OnNameChanged(val name: String) : ClassFormEvent()
    data class OnDaysOfWeekChanged(val daysOfWeek: List<Boolean>) : ClassFormEvent()
    data class OnStartingTimeChanged(val startingTime: String) : ClassFormEvent()
    data class OnEndingTimeChanged(val endingTime: String) : ClassFormEvent()

    data object RemoveCurrentClass : ClassFormEvent()
    data object OnSubmit : ClassFormEvent()
}


