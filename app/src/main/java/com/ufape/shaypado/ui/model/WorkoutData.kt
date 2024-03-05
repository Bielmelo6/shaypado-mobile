package com.ufape.shaypado.ui.model


data class WorkoutData(
    val id: String?,
    val name: String,
    val category: String,
    val exercises: List<ExerciseData>,
)

data class ExerciseData(
    val id: String,
    val title: String,
    val description: String,
    val videoUrl: String?,
    val series: Int,
    val repetitions: Int,
    val time: String,
)