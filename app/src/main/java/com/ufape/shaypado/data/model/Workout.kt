package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.ExerciseData
import com.ufape.shaypado.ui.model.ExerciseState
import com.ufape.shaypado.ui.model.WorkoutData
import com.ufape.shaypado.ui.model.WorkoutState

data class CreateWorkoutRequest(
    @SerializedName("title")
    val name: String,
    @SerializedName("workoutType")
    val category: String,
    @SerializedName("exercises")
    val exercises: List<String>,
)

data class UpdateWorkoutRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("exercises")
    val exercises: List<String>,
)


data class WorkoutResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val name: String,
    @SerializedName("workoutType")
    val category: String,
    @SerializedName("exercises")
    val exercises: List<ExerciseResponse>,
    @SerializedName("endWorkout")
    val endWorkout: Boolean
)

data class WorkoutIdRequest (
    @SerializedName("workout_id")
    val workoutId: String
)

fun WorkoutResponse.toUiModel(): WorkoutState {
    return WorkoutState(
        id = this.id,
        name = this.name,
        category = this.category,
        endWorkout = this.endWorkout,
        exercises = this.exercises.map { it.toUiModel() }
    )
}

