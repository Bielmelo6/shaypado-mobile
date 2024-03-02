package com.ufape.shaypado.ui.screens.trainer.createUser

import androidx.annotation.StringRes

data class UserFormState(
    val userType : String = "",
    @StringRes val userTypeError: Int? = null,
    val name: String = "",
    @StringRes val nameError: Int? = null,
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val emailConfirmation: String = "",
    @StringRes val emailConfirmationError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null,
    val passwordConfirmation: String = "",
    @StringRes  val passwordConfirmationError: Int? = null,
    val workoutType: String = "",
    @StringRes val workoutTypeError: Int? = null,
    val saveCorporalData: Boolean = false,
    @StringRes val saveCorporalDataError: Int? = null,
    val termsAccepted: Boolean = false,
    @StringRes val termsAcceptedError: Int? = null,


    val fatPercentage: String = "",
    @StringRes val fatPercentageError: Int? = null,
    val armCircumference: String = "",
    @StringRes val armCircumferenceError: Int? = null,
    val waistCircumference: String = "",
    @StringRes val waistCircumferenceError: Int? = null,
    val abdomenCircumference: String = "",
    @StringRes val abdomenCircumferenceError: Int? = null,
    val hipCircumference: String = "",
    @StringRes val hipCircumferenceError: Int? = null,
    val thighCircumference: String = "",
    @StringRes val thighCircumferenceError: Int? = null,
    val legCircumference: String = "",
    @StringRes val legCircumferenceError: Int? = null,
    val height: String = "",
    @StringRes val heightError: Int? = null,
    val weight: String = "",
    @StringRes val weightError: Int? = null,
    val age: String = "",
    @StringRes val ageError: Int? = null,
    val shoulderCircumference: String = "",
    @StringRes val shoulderCircumferenceError: Int? = null,
    val tricepsFold: String = "",
    @StringRes val tricepsFoldError: Int? = null,
    val bicepsFold: String = "",
    @StringRes val bicepsFoldError: Int? = null,
    val chestFold: String = "",
    @StringRes val chestFoldError: Int? = null,
    val axialFold: String = "",
    @StringRes val axialFoldError: Int? = null,
    val suprailiacFold: String = "",
    @StringRes val suprailiacFoldError: Int? = null,
    val abdominalFold: String = "",
    @StringRes val abdominalFoldError: Int? = null,
    val thighFold: String = "",
    @StringRes val thighFoldError: Int? = null,
    val legFold: String = "",
    @StringRes val legFoldError: Int? = null,
    val healthIssue: String = "",
    @StringRes val healthIssueError: Int? = null,
    val scapularFold: String = "",
    @StringRes val scapularFoldError: Int? = null,
    val objective: String = "",
    @StringRes val objectiveError: Int? = null,
    val exerciseExperience: Boolean = false,
    val spineProblem: Boolean = false,
    val isSmoker: Boolean = false
)

sealed class UserFormEvent {
    data class OnNameChanged(val name: String) : UserFormEvent()
    data class OnEmailChanged(val email: String) : UserFormEvent()
    data class OnEmailConfirmationChanged(val emailConfirmation: String) : UserFormEvent()
    data class OnPasswordChanged(val password: String) : UserFormEvent()
    data class OnPasswordConfirmationChanged(val passwordConfirmation: String) : UserFormEvent()
    data class OnCorporalDataChanged(val saveCorporalData: Boolean) : UserFormEvent()
    data class OnTermsAcceptedChanged(val termsAccepted: Boolean) : UserFormEvent()
    data class OnUserTypeChanged(val userType: String) : UserFormEvent()

    data class OnFatPercentageChanged(val fatPercentage: String) : UserFormEvent()
    data class OnArmCircumferenceChanged(val armCircumference: String) : UserFormEvent()
    data class OnWaistCircumferenceChanged(val waistCircumference: String) : UserFormEvent()
    data class OnAbdomenCircumferenceChanged(val abdomenCircumference: String) : UserFormEvent()
    data class OnHipCircumferenceChanged(val hipCircumference: String) : UserFormEvent()
    data class OnThighCircumferenceChanged(val thighCircumference: String) : UserFormEvent()
    data class OnLegCircumferenceChanged(val legCircumference: String) : UserFormEvent()
    data class OnHeightChanged(val height: String) : UserFormEvent()
    data class OnWeightChanged(val weight: String) : UserFormEvent()
    data class OnAgeChanged(val age: String) : UserFormEvent()
    data class OnShoulderCircumferenceChanged(val shoulderCircumference: String) : UserFormEvent()
    data class OnTricepsFoldChanged(val tricepsFold: String) : UserFormEvent()
    data class OnBicepsFoldChanged(val bicepsFold: String) : UserFormEvent()
    data class OnChestFoldChanged(val chestFold: String) : UserFormEvent()
    data class OnAxialFoldChanged(val axialFold: String) : UserFormEvent()
    data class OnSuprailiacFoldChanged(val suprailiacFold: String) : UserFormEvent()
    data class OnAbdominalFoldChanged(val abdominalFold: String) : UserFormEvent()
    data class OnThighFoldChanged(val thighFold: String) : UserFormEvent()
    data class OnLegFoldChanged(val legFold: String) : UserFormEvent()
    data class OnHealthIssueChanged(val healthIssue: String) : UserFormEvent()
    data class OnExerciseExperienceChanged(val exerciseExperience: Boolean) : UserFormEvent()
    data class OnSpineProblemChanged(val hasSpineProblem: Boolean) : UserFormEvent()
    data class OnSmokerChanged(val isSmoker: Boolean) : UserFormEvent()
    data class OnScapularFoldChanged(val scapularFold: String) : UserFormEvent()
    data class OnObjectiveChanged(val objective: String) : UserFormEvent()

    data object OnSubmit : UserFormEvent()
}
