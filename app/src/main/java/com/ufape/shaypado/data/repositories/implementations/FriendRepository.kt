package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.FriendApi
import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IFriendRepository
import com.ufape.shaypado.ui.model.FriendsState
import com.ufape.shaypado.util.getApiError
import com.ufape.shaypado.util.Result

class FriendRepository (
    private val api : FriendApi
) : IFriendRepository {
    override suspend fun getFriends(): Result<FriendsState> {
        val result = api.getFriends()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun addFriend(friendCode: FriendshipCodeRequest): Result<Unit> {
        val result = api.addFriend(friendCode)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun removeFriend(friendCode: FriendshipCodeRequest): Result<Unit> {
        val result = api.removeFriend(friendCode)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun acceptFriend(friendCode: FriendshipCodeRequest): Result<Unit> {
        val result = api.acceptFriend(friendCode)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun denyFriend(friendCode: FriendshipCodeRequest): Result<Unit> {
        val result = api.denyFriend(friendCode)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun getPendingFriends(): Result<List<FriendsState>> {
        val result = api.getPendingFriends()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.map { it.toUiModel() })
        } else {
            Result.Error(result.getApiError())
        }
    }

}