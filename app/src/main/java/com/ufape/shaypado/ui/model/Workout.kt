package com.ufape.shaypado.ui.model

import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest

data class WorkoutState(
    var isSelected : Boolean = false,
    val id: String = "",
    val name: String = "",
    val category: String = "",
    val categoryId: String = "",
    val exercises: List<ExerciseState> = emptyList(),
)

fun WorkoutState.toCreateRequest(): CreateWorkoutRequest {
    return CreateWorkoutRequest(
        name = this.name,
        category = this.categoryId,
        exercises = this.exercises.map { it.toRequest() }
    )
}

fun WorkoutState.toUpdateRequest(): UpdateWorkoutRequest {
    return UpdateWorkoutRequest(
        id = this.id,
        name = this.name,
        category = this.categoryId,
        exercises = this.exercises.map { it.id }
    )
}

