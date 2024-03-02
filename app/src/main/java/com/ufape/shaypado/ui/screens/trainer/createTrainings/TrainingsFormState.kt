package com.ufape.shaypado.ui.screens.trainer.createTrainings

import androidx.annotation.StringRes
import com.ufape.shaypado.data.model.ExerciseRequest
import com.ufape.shaypado.data.model.WorkoutRequest

data class TrainingsFormState(
    val name: String = "",
    @StringRes val nameError: Int? = null,
    val category: String = "",
    @StringRes val categoryError: Int? = null,
    var exercises: List<ExerciseFormState> = emptyList(),
)

data class ExerciseFormState(
    val title: String = "",
    @StringRes val titleError: Int? = null,
    val description: String = "",
    @StringRes val descriptionError: Int? = null,
    val category: String = "",
    @StringRes val categoryError: Int? = null,
    val videoUrl : String = "",
    @StringRes val videoUrlError: Int? = null,
    val series : String = "0",
    @StringRes val seriesError: Int? = null,
    val repetitions : String = "0",
    @StringRes val repetitionsError: Int? = null,
    val time : String = "00:00",
    @StringRes val timeError: Int? = null,
)

fun TrainingsFormState.toRequest() = WorkoutRequest(
    name = name,
    category = category,
    exercises = exercises.map { it.toRequest() }
)

fun ExerciseFormState.toRequest() = ExerciseRequest(
    title = title,
    category = category,
    description = description,
    videoUrl = videoUrl,
    series = series.toInt(),
    repetitions = repetitions.toInt(),
    time = time
)

sealed class TrainingsFormEvent {
    data class OnNameChanged(val name: String) : TrainingsFormEvent()
    data class OnCategoryChanged(val category: String) : TrainingsFormEvent()
    data class OnExercisesChanged(val exercises: List<ExerciseFormState>) : TrainingsFormEvent()
    data object RemoveCurrentTraining : TrainingsFormEvent()
    data object OnSubmit : TrainingsFormEvent()
}

sealed class ExerciseFormEvent {
    data class OnTitleChanged(val title: String) : ExerciseFormEvent()
    data class OnDescriptionChanged(val description: String) : ExerciseFormEvent()
    data class OnCategoryChanged(val category: String) : ExerciseFormEvent()
    data class OnVideoUrlChanged(val videoUrl: String) : ExerciseFormEvent()
    data class OnSeriesChanged(val series: String) : ExerciseFormEvent()
    data class OnRepetitionsChanged(val repetitions: String) : ExerciseFormEvent()
    data class OnTimeChanged(val time: String) : ExerciseFormEvent()
    data class RemoveCurrentExercise(val index: Int) : ExerciseFormEvent()
    data object OnSubmit : ExerciseFormEvent()
}