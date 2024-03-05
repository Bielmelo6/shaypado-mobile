package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.FriendsResponse
import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.PendingFriendResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface FriendApi {

    @Headers("Content-Type: application/json")
    @GET("/friends")
    suspend fun getFriends() : Response<FriendsResponse>

    @Headers("Content-Type: application/json")
    @POST("/add_friend")
    suspend fun addFriend(@Body data : FriendshipCodeRequest) : Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/delete_friend")
    suspend fun removeFriend(@Body data: FriendshipCodeRequest) : Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/accept_friend")
    suspend fun acceptFriend(@Body data: FriendshipCodeRequest) : Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/deny_friend")
    suspend fun denyFriend(@Body data: FriendshipCodeRequest) : Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/pending_friends")
    suspend fun getPendingFriends() : Response<List<FriendsResponse>>
}