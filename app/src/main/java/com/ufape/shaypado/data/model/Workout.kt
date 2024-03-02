package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName

data class WorkoutRequest(
    @SerializedName("title")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("exercises")
    val exercises: List<ExerciseRequest>,
)

data class ExerciseRequest(
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: String,
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

