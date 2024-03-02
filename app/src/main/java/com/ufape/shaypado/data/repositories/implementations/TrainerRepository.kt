package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.TrainerApi
import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.WorkoutRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.FriendsData
import com.ufape.shaypado.ui.model.TrainerProfileData
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

    override suspend fun fetchFriends(): Result<FriendsData> {
        val result = api.fetchFriendsData()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun removeFriend(friendshipCode: FriendshipCodeRequest): Result<Unit> {
        val result = api.removeFriend(friendshipCode)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun addFriend(friendshipCode: FriendshipCodeRequest): Result<Unit> {
        val result = api.addFriend(friendshipCode)
        return if (result.isSuccessful) {
            Result.Success(Unit)
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

    override suspend fun createTraining(profile: List<WorkoutRequest>): Result<Unit> {
        val result = api.createTraining(profile)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }
}