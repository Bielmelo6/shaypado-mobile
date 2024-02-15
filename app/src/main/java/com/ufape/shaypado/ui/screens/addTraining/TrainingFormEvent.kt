package com.ufape.shaypado.ui.screens.addTraining

sealed class TrainingFormEvent {
    data class OnNameChanged(val name: String) : TrainingFormEvent()
    data class OnDaysOfWeekChanged(val daysOfWeek: List<Boolean>) : TrainingFormEvent()
    data class OnTrainingStartTimeChanged(val trainingStartTime: String) : TrainingFormEvent()
    data class OnTrainingEndTimeChanged(val trainingEndTime: String) : TrainingFormEvent()
    data class OnTrainingTypeChanged(val trainingType: String) : TrainingFormEvent()
    data class OnExercisesChanged(val exercises: List<String>) : TrainingFormEvent()
    data object OnSubmit : TrainingFormEvent()
}
