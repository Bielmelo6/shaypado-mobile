package com.ufape.shaypado.ui.screens.signUp

import androidx.annotation.StringRes
import com.ufape.shaypado.data.model.UserRequest

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
    val saveCorporalData: Boolean = false,
    @StringRes val saveCorporalDataError: Int? = null,
    val termsAccepted: Boolean = false,
    @StringRes val termsAcceptedError: Int? = null,
)

fun UserAccountFormState.toRequest(physicalEvaluationData : UserPhysicalEvaluationFormState) : UserRequest {
    return UserRequest(
        userType = userType,
        name = name,
        email = email,
        password = password,
        weight = physicalEvaluationData.weight,
        height = physicalEvaluationData.height,
        anyDisease = physicalEvaluationData.healthIssue,
    )
}
