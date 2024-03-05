package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.ui.model.FriendsState
import com.ufape.shaypado.util.Result

interface IFriendRepository {
    suspend fun getFriends(): Result<FriendsState>
    suspend fun addFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun removeFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun acceptFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun denyFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun getPendingFriends(): Result<List<FriendsState>>
}