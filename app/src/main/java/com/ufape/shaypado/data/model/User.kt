package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.UserState

data class UserRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,

    @SerializedName("fat_percentage")
    val fatPercentage: String?,
    @SerializedName("arm_circumference")
    val armCircumference: String?,
    @SerializedName("waist_circumference")
    val waistCircumference: String?,
    @SerializedName("abdomen_circumference")
    val abdomenCircumference: String?,
    @SerializedName("hip_circumference")
    val hipCircumference: String?,
    @SerializedName("thigh_circumference")
    val thighCircumference: String?,
    @SerializedName("leg_circumference")
    val legCircumference: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("age")
    val age: String?,
    @SerializedName("shoulder_circumference")
    val shoulderCircumference: String?,
    @SerializedName("triceps_fold")
    val tricepsFold: String?,
    @SerializedName("biceps_fold")
    val bicepsFold: String?,
    @SerializedName("chest_fold")
    val chestFold: String?,
    @SerializedName("axial_fold")
    val axialFold: String?,
    @SerializedName("suprailiac_fold")
    val suprailiacFold: String?,
    @SerializedName("abdominal_fold")
    val abdominalFold: String?,
    @SerializedName("thigh_fold")
    val thighFold: String?,
    @SerializedName("leg_fold")
    val legFold: String?,
    @SerializedName("health_issue")
    val healthIssue: String?,
    @SerializedName("scapular_fold")
    val scapularFold: String?,
    @SerializedName("objective")
    val objective: String?,
    @SerializedName("exercise_experience")
    val exerciseExperience: Boolean?,
    @SerializedName("spine_problem")
    val spineProblem: Boolean,
    @SerializedName("is_smoker")
    val isSmoker: Boolean,
    @SerializedName("gender")
    val gender: String?,
)

data class UserResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,

    @SerializedName("fat_percentage")
    val fatPercentage: String?,
    @SerializedName("arm_circumference")
    val armCircumference: String?,
    @SerializedName("waist_circumference")
    val waistCircumference: String?,
    @SerializedName("abdomen_circumference")
    val abdomenCircumference: String?,
    @SerializedName("hip_circumference")
    val hipCircumference: String?,
    @SerializedName("thigh_circumference")
    val thighCircumference: String?,
    @SerializedName("leg_circumference")
    val legCircumference: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("age")
    val age: String?,
    @SerializedName("shoulder_circumference")
    val shoulderCircumference: String?,
    @SerializedName("triceps_fold")
    val tricepsFold: String?,
    @SerializedName("biceps_fold")
    val bicepsFold: String?,
    @SerializedName("chest_fold")
    val chestFold: String?,
    @SerializedName("axial_fold")
    val axialFold: String?,
    @SerializedName("suprailiac_fold")
    val suprailiacFold: String?,
    @SerializedName("abdominal_fold")
    val abdominalFold: String?,
    @SerializedName("thigh_fold")
    val thighFold: String?,
    @SerializedName("leg_fold")
    val legFold: String?,
    @SerializedName("health_issue")
    val healthIssue: String?,
    @SerializedName("scapular_fold")
    val scapularFold: String?,
    @SerializedName("objective")
    val objective: String?,
    @SerializedName("exercise_experience")
    val exerciseExperience: Boolean?,
    @SerializedName("spine_problem")
    val spineProblem: Boolean,
    @SerializedName("is_smoker")
    val isSmoker: Boolean,
    @SerializedName("gender")
    val gender: String?,
)


data class PhishycalEvaluation(
    @SerializedName("fat_percentage")
    val fatPercentage: String?,
    @SerializedName("arm_circumference")
    val armCircumference: String?,
    @SerializedName("waist_circumference")
    val waistCircumference: String?,
    @SerializedName("abdomen_circumference")
    val abdomenCircumference: String?,
    @SerializedName("hip_circumference")
    val hipCircumference: String?,
    @SerializedName("thigh_circumference")
    val thighCircumference: String?,
    @SerializedName("leg_circumference")
    val legCircumference: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("age")
    val age: String?,
    @SerializedName("shoulder_circumference")
    val shoulderCircumference: String?,
    @SerializedName("triceps_fold")
    val tricepsFold: String?,
    @SerializedName("biceps_fold")
    val bicepsFold: String?,
    @SerializedName("chest_fold")
    val chestFold: String?,
    @SerializedName("axial_fold")
    val axialFold: String?,
    @SerializedName("suprailiac_fold")
    val suprailiacFold: String?,
    @SerializedName("abdominal_fold")
    val abdominalFold: String?,
    @SerializedName("thigh_fold")
    val thighFold: String?,
    @SerializedName("leg_fold")
    val legFold: String?,
    @SerializedName("health_issue")
    val healthIssue: String?,
    @SerializedName("scapular_fold")
    val scapularFold: String?,
    @SerializedName("objective")
    val objective: String?,
    @SerializedName("exercise_experience")
    val exerciseExperience: Boolean?,
    @SerializedName("spine_problem")
    val spineProblem: Boolean,
    @SerializedName("is_smoker")
    val isSmoker: Boolean,
    @SerializedName("gender")
    val gender: String?,
)

fun UserResponse.toUiModel(): UserState {
    return UserState(
        name = this.name,
        email = this.email,

        fatPercentage = this.fatPercentage,
        armCircumference = this.armCircumference,
        waistCircumference = this.waistCircumference,
        abdomenCircumference = this.abdomenCircumference,
        hipCircumference = this.hipCircumference,
        thighCircumference = this.thighCircumference,
        legCircumference = this.legCircumference,
        height = this.height,
        weight = this.weight,
        age = this.age,
        shoulderCircumference = this.shoulderCircumference,
        tricepsFold = this.tricepsFold,
        bicepsFold = this.bicepsFold,
        chestFold = this.chestFold,
        axialFold = this.axialFold,
        suprailiacFold = this.suprailiacFold,
        abdominalFold = this.abdominalFold,
        thighFold = this.thighFold,
        legFold = this.legFold,
        healthIssue = this.healthIssue,
        scapularFold = this.scapularFold,
        objective = this.objective,
        spineProblem = this.spineProblem,
        isSmoker = this.isSmoker,
        gender = this.gender,
        exerciseExperience = this.exerciseExperience
    )
}

