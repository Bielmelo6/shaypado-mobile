package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.TrainerApi
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.ui.model.UserState
import com.ufape.shaypado.ui.model.UserWithWorkoutState
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getApiError

class TrainerRepository(
    private val api: TrainerApi
) : ITrainerRepository {
    override suspend fun fetchTrainerProfile(): Result<TrainerProfileData> {
        val result = api.fetchProfileData()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }
    override suspend fun registerUsers(users: List<UserRequest>): Result<Unit> {
        val result = api.registerUsers(users)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun fetchStudent(id: String): Result<UserWithWorkoutState> {
        val result = api.fetchStudent(id)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }
}