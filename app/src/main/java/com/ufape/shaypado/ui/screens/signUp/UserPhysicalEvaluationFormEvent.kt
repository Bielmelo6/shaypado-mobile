package com.ufape.shaypado.ui.screens.signUp

sealed class UserPhysicalEvaluationFormEvent {
    data class OnFatPercentageChanged(val fatPercentage: String) : UserPhysicalEvaluationFormEvent()
    data class OnArmCircumferenceChanged(val armCircumference: String) : UserPhysicalEvaluationFormEvent()
    data class OnWaistCircumferenceChanged(val waistCircumference: String) : UserPhysicalEvaluationFormEvent()
    data class OnAbdomenCircumferenceChanged(val abdomenCircumference: String) : UserPhysicalEvaluationFormEvent()
    data class OnHipCircumferenceChanged(val hipCircumference: String) : UserPhysicalEvaluationFormEvent()
    data class OnThighCircumferenceChanged(val thighCircumference: String) : UserPhysicalEvaluationFormEvent()

    data object OnSubmit : UserPhysicalEvaluationFormEvent()
}