package com.ufape.shaypado.ui.screens.signUp

sealed class UserPhysicalEvaluationFormEvent {
    data class OnWeightChanged(val weight: String) : UserPhysicalEvaluationFormEvent()
    data class OnHeightChanged(val height: String) : UserPhysicalEvaluationFormEvent()
    data class OnObjectiveChanged(val objective: String) : UserPhysicalEvaluationFormEvent()
    data class OnWorkoutFrequencyChanged(val workoutFrequency: String) : UserPhysicalEvaluationFormEvent()
    data class OnAnyDiseaseChanged(val anyDisease: Boolean) : UserPhysicalEvaluationFormEvent()
    data object OnSubmit : UserPhysicalEvaluationFormEvent()
}
