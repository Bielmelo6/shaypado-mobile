package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.ExerciseState

data class CreateExerciseRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("workoutType")
    val workoutType: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("video_url")
    val videoUrl: String?,
    @SerializedName("series")
    val series: String,
    @SerializedName("repetitions")
    val repetitions: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("end_exercise")
    val endExercise: Boolean = false,
)

data class UpdateExerciseRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val description: String,
    @SerializedName("video_url")
    val videoUrl: String?,
    @SerializedName("series")
    val series: String,
    @SerializedName("repetitions")
    val repetitions: String,
    @SerializedName("time")
    val time: String,
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
    val series: String,
    @SerializedName("repetitions")
    val repetitions: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("end_exercise")
    val endExercise: Boolean = false
)

fun ExerciseResponse.toUiModel(): ExerciseState {
    return ExerciseState(
        id = this.id,
        title = this.title,
        description = this.description,
        videoUrl = this.videoUrl,
        series = this.series,
        repetitions = this.repetitions,
        time = this.time,
        isEndExercise = this.endExercise
    )
}