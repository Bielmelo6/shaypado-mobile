package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.FriendsResponse
import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.PendingFriendResponse

interface IFriendRepository {
    suspend fun getFriends(): Result<FriendsResponse>
    suspend fun addFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun removeFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun acceptFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun denyFriend(friendCode : FriendshipCodeRequest): Result<Unit>
    suspend fun getPendingFriends(): Result<List<PendingFriendResponse>>
}