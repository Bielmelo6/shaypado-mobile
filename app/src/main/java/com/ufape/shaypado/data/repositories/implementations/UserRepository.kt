package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.UserApi
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IUserRepository
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getApiError

class UserRepository (
    private val api : UserApi
) : IUserRepository {
    override suspend fun fetchWorkouts() : Result<List<WorkoutState>> {
        val result = api.fetchWorkouts()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.map { it.toUiModel() })
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun fetchWorkout(id : String): Result<WorkoutState> {
        TODO("Not yet implemented")
    }

    override suspend fun concludeWorkout(id: String): Result<Unit> {
        val result = api.concludeWorkout(id)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun undoWorkout(id: String): Result<Unit> {
        val result = api.undoWorkout(id)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun concludeExercise(id: String): Result<Unit> {
        val result = api.concludeExercise(id)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun undoExercise(id: String): Result<Unit> {
        val result = api.undoExercise(id)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun fetchProfessionals() {
    }

    override suspend fun fetchProfessional() {
    }

}