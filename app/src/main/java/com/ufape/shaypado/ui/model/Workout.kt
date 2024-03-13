package com.ufape.shaypado.ui.model

import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest

data class WorkoutState(
    var isSelected : Boolean = false,
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val exercises: List<ExerciseState> = emptyList(),
    val endWorkout: Boolean = false
)

fun WorkoutState.toCreateRequest(): CreateWorkoutRequest {
    return CreateWorkoutRequest(
        name = this.name,
        category = this.category,
        exercises = this.exercises.map { it.id }
    )
}

fun WorkoutState.toUpdateRequest(): UpdateWorkoutRequest {
    return UpdateWorkoutRequest(
        id = this.id,
        name = this.name,
        category = this.category,
        exercises = this.exercises.map { it.id }
    )
}

