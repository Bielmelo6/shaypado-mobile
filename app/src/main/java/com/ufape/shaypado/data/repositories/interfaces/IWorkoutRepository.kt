package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.CategoryResponse
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.model.WorkoutResponse

interface IWorkoutRepository {
    suspend fun createWorkouts(workouts: List<CreateWorkoutRequest>): Result<Unit>

    suspend fun getWorkouts(): Result<List<WorkoutResponse>>

    suspend fun getWorkout(id: String): Result<WorkoutResponse>

    suspend fun updateWorkout(workoutData: UpdateWorkoutRequest): Result<WorkoutResponse>

    suspend fun deleteWorkout(id: String): Result<Unit>

    suspend fun fetchWorkoutCategories(): Result<List<CategoryResponse>>
}