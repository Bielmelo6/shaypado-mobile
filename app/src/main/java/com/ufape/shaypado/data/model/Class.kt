package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName


data class ClassResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val daysOfWeek: List<Boolean>,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("students")
    val students : List<ClassStudentResponse>,
    @SerializedName("workouts")
    val workouts : List<ClassWorkoutResponse>
)

data class ClassStudentResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class ClassWorkoutResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("category")
    val category: String,
)

data class CreateClassRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val daysOfWeek: List<Boolean>,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("students")
    val students : List<String>,
    @SerializedName("workouts")
    val workouts : List<String>
)

data class UpdateClassRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val daysOfWeek: List<Boolean>,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("students")
    val students : List<String>,
    @SerializedName("workouts")
    val workouts : List<String>
)