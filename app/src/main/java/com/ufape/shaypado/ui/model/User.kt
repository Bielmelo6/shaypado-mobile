package com.ufape.shaypado.ui.model


data class UserState(
    val name: String,
    val email: String,
    val password: String,

    val fatPercentage: String?,
    val armCircumference: String?,
    val waistCircumference: String?,
    val abdomenCircumference: String?,
    val hipCircumference: String?,
    val thighCircumference: String?,
    val legCircumference: String?,
    val height: String?,
    val weight: String?,
    val age: String?,
    val shoulderCircumference: String?,
    val tricepsFold: String?,
    val bicepsFold: String?,
    val chestFold: String?,
    val axialFold: String?,
    val suprailiacFold: String?,
    val abdominalFold: String?,
    val thighFold: String?,
    val legFold: String?,
    val healthIssue: String?,
    val scapularFold: String?,
    val objective: String?,
    val exerciseExperience: Boolean,
    val spineProblem: Boolean,
    val isSmoker: Boolean
)

