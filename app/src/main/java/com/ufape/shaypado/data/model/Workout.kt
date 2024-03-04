package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.ExerciseData
import com.ufape.shaypado.ui.model.WorkoutData

data class WorkoutRequest(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("title")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("exercises")
    val exercises: List<ExerciseRequest>,
)

data class ExerciseRequest(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val description: String,
    @SerializedName("video_url")
    val videoUrl: String?,
    @SerializedName("series")
    val series: Int,
    @SerializedName("repetitions")
    val repetitions: Int,
    @SerializedName("time")
    val time: String,
)


data class WorkoutResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("exercises")
    val exercises: List<ExerciseResponse>,
)

data class ExerciseResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("video_url")
    val videoUrl: String?,
    @SerializedName("series")
    val series: Int,
    @SerializedName("repetitions")
    val repetitions: Int,
    @SerializedName("time")
    val time: String,
)

data class WorkoutIdRequest (
    @SerializedName("workout_id")
    val workoutId: String
)

fun WorkoutResponse.toUiModel(): WorkoutData {
    return WorkoutData(
        id = this.id,
        name = this.name,
        category = this.category,
        exercises = this.exercises.map { it.toUiModel() }
    )
}

fun ExerciseResponse.toUiModel(): ExerciseData {
    return ExerciseData(
        id = this.id,
        title = this.title,
        description = this.description,
        videoUrl = this.videoUrl,
        series = this.series,
        repetitions = this.repetitions,
        time = this.time,
    )
}

