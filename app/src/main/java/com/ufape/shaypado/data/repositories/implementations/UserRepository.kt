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

    override suspend fun concludeWorkout() {
        TODO("Not yet implemented")
    }

    override suspend fun concludeExercise() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchProfessionals() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchProfessional() {
        TODO("Not yet implemented")
    }

}