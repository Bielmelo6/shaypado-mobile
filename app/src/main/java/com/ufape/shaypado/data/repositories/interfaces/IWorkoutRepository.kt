package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.CategoryResponse
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.model.WorkoutResponse
import com.ufape.shaypado.ui.model.CategoryState
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.Result

interface IWorkoutRepository {
    suspend fun createWorkouts(workouts: List<CreateWorkoutRequest>): Result<Unit>

    suspend fun getWorkouts(): Result<List<WorkoutState>>

    suspend fun getWorkout(id: String): Result<WorkoutState>

    suspend fun updateWorkout(workoutData: UpdateWorkoutRequest): Result<WorkoutState>

    suspend fun deleteWorkout(id: String): Result<Unit>

    suspend fun fetchWorkoutCategories(): Result<List<CategoryState>>
}