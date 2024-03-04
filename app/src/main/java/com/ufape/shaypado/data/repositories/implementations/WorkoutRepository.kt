package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.WorkoutApi
import com.ufape.shaypado.data.model.CategoryResponse
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.model.WorkoutResponse
import com.ufape.shaypado.data.repositories.interfaces.IWorkoutRepository

class WorkoutRepository(
    private val api: WorkoutApi
) : IWorkoutRepository {
    override suspend fun createWorkouts(workouts: List<CreateWorkoutRequest>): Result<Unit> {
        val result = api.createWorkouts(workouts)
        return if (result.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun getWorkouts(): Result<List<WorkoutResponse>> {
        val result = api.getWorkouts()
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun getWorkout(id: String): Result<WorkoutResponse> {
        val result = api.getWorkout(id)
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun updateWorkout(workoutData: UpdateWorkoutRequest): Result<WorkoutResponse> {
        val result = api.updateWorkout(workoutData)
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun deleteWorkout(id: String): Result<Unit> {
        val result = api.deleteWorkout(id)
        return if (result.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(Throwable(result.message()))
        }
    }

    override suspend fun fetchWorkoutCategories(): Result<List<CategoryResponse>> {
        val result = api.fetchWorkoutCategories()
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(Throwable(result.message()))
        }
    }
}