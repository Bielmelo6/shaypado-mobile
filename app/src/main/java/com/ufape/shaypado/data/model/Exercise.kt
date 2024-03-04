package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName

data class CreateExerciseRequest(
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
    val series: Int,
    @SerializedName("repetitions")
    val repetitions: Int,
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
    val series: Int,
    @SerializedName("repetitions")
    val repetitions: Int,
    @SerializedName("time")
    val time: String,
)