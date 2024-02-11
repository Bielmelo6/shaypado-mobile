package com.ufape.shaypado.ui.screens.signUp

import androidx.annotation.StringRes

data class UserAccountFormState(
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
    val weight: String = "",
    @StringRes val weightError: Int? = null,
    val height: String = "",
    @StringRes val heightError: Int? = null,
    val objective: String = "",
    @StringRes  val objectiveError: Int? = null,
    val anyDisease: Boolean = false,
    @StringRes val anyDiseaseError: Int? = null,
    val saveCorporalData: Boolean = false,
    @StringRes val saveCorporalDataError: Int? = null,
    val termsAccepted: Boolean = false,
    @StringRes val termsAcceptedError: Int? = null,
)
