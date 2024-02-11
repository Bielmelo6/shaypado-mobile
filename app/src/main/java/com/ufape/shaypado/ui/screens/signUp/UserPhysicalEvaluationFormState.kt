package com.ufape.shaypado.ui.screens.signUp

import androidx.annotation.StringRes

data class UserPhysicalEvaluationFormState(
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
    @StringRes val thighCircumferenceError: Int? = null
)
