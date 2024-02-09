package com.ufape.shaypado.ui.screens.addTraining

sealed class ExerciseFormEvent {
    data class OnNameChanged(val name: String) : ExerciseFormEvent()
    data class OnSeriesChanged(val series: Int) : ExerciseFormEvent()
    data class OnRepetitionsChanged(val repetitions: Int) : ExerciseFormEvent()
    data class OnCaloriesChanged(val calories: Int) : ExerciseFormEvent()
    data object OnSubmit : ExerciseFormEvent()
}
