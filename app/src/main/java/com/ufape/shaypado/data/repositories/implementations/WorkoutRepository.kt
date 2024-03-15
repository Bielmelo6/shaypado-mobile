package com.ufape.shaypado.data.repositories.implementations

import android.util.Log
import com.ufape.shaypado.data.api.WorkoutApi
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IWorkoutRepository
import com.ufape.shaypado.ui.model.CategoryState
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getApiError

class WorkoutRepository(
    private val api: WorkoutApi
) : IWorkoutRepository {
    override suspend fun createWorkouts(workouts: List<CreateWorkoutRequest>): Result<Unit> {
        val result = api.createWorkouts(workouts)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun getWorkouts(): Result<List<WorkoutState>> {
        val result = api.getWorkouts()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.map { it.toUiModel() })
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun getWorkout(id: String): Result<WorkoutState> {
        val result = api.getWorkout(id)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun updateWorkout(workoutData: UpdateWorkoutRequest): Result<WorkoutState> {
        val result = api.updateWorkout(workoutData)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun deleteWorkout(id: String): Result<Unit> {
        val result = api.deleteWorkout(id)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun fetchWorkoutCategories(): Result<List<CategoryState>> {
        val result = api.fetchWorkoutCategories()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.map { it.toUiModel() })
        } else {
            Result.Error(result.getApiError())
        }
    }
}