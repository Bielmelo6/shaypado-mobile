package com.ufape.shaypado.ui.model

import androidx.annotation.StringRes
import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.UpdateExerciseRequest

data class ExerciseState(
    val id: String = "",
    val title: String = "",
    @StringRes val titleError: Int? = null,
    val description: String = "",
    @StringRes val descriptionError: Int? = null,
    val videoUrl: String? = "",
    @StringRes val videoUrlError: Int? = null,
    val series: String = "",
    @StringRes val seriesError: Int? = null,
    val repetitions: String = "",
    @StringRes val repetitionsError: Int? = null,
    val time: String = "00:00",
    @StringRes val timeError: Int? = null,
    val category: String = "",
    val categoryId: String = "",
    @StringRes val categoryIdError: Int? = null,
    val isEndExercise: Boolean = false
)

fun ExerciseState.toRequest() = CreateExerciseRequest(
    title = title,
    description = description,
    videoUrl = videoUrl,
    series = series,
    repetitions = repetitions,
    time = time,
    workoutType = listOf(categoryId)
)

fun ExerciseState.toUpdateRequest() = UpdateExerciseRequest(
    id = id,
    title = title,
    description = description,
    videoUrl = videoUrl,
    series = series,
    repetitions = repetitions,
    time = time
)