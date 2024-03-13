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
        name = name,
        email = email,
        password = password,
        weight = physicalEvaluationData.weight,
        height = physicalEvaluationData.height,
        fatPercentage = physicalEvaluationData.fatPercentage,
        armCircumference = physicalEvaluationData.armCircumference,
        waistCircumference = physicalEvaluationData.waistCircumference,
        abdomenCircumference = physicalEvaluationData.abdomenCircumference,
        hipCircumference = physicalEvaluationData.hipCircumference,
        thighCircumference = physicalEvaluationData.thighCircumference,
        legCircumference = physicalEvaluationData.legCircumference,
        age = physicalEvaluationData.age,
        shoulderCircumference = physicalEvaluationData.shoulderCircumference,
        tricepsFold = physicalEvaluationData.tricepsFold,
        bicepsFold = physicalEvaluationData.bicepsFold,
        chestFold = physicalEvaluationData.chestFold,
        axialFold = physicalEvaluationData.axialFold,
        suprailiacFold = physicalEvaluationData.suprailiacFold,
        abdominalFold = physicalEvaluationData.abdominalFold,
        thighFold = physicalEvaluationData.thighFold,
        legFold = physicalEvaluationData.legFold,
        healthIssue = physicalEvaluationData.healthIssue,
        scapularFold = physicalEvaluationData.scapularFold,
        objective = physicalEvaluationData.objective,
        exerciseExperience = physicalEvaluationData.exerciseExperience,
        spineProblem = physicalEvaluationData.spineProblem,
        isSmoker = physicalEvaluationData.isSmoker,
        gender = physicalEvaluationData.gender
    )
}
