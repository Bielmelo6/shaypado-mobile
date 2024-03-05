package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.ClassState
import com.ufape.shaypado.ui.model.ClassWorkoutState
import com.ufape.shaypado.ui.model.FriendState


data class ClassResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("days_of_week")
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

fun ClassResponse.toUiModel(): ClassState {
    return ClassState(
        id = this.id,
        name = this.name,
        daysOfWeek = this.daysOfWeek,
        startTime = this.startTime,
        endTime = this.endTime,
        students = this.students.map { it.toUiModel() },
        workouts = this.workouts.map { it.toUiModel() }
    )
}

data class ClassStudentResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

fun ClassStudentResponse.toUiModel() = FriendState(
    friendshipCode = this.id,
    name = this.name
)

data class ClassWorkoutResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("category")
    val category: String,
)

fun ClassWorkoutResponse.toUiModel() = ClassWorkoutState(
    id = this.id,
    categoryId = this.categoryId,
    category = this.category,
    title = this.title
)

data class CreateClassRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("days_of_week")
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