package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("userType")
    val userType: String,

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
    val spineProblem: Boolean ,
    @SerializedName("is_smoker")
    val isSmoker: Boolean
)

data class UserResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("userType")
    val userType: String,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("objective")
    val objective : String?,
    @SerializedName("any_disease")
    val anyDisease : String
)

