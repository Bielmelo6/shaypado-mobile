package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.FriendsResponse
import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.TrainerResponse
import com.ufape.shaypado.data.model.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TrainerApi {
    @Headers("Content-Type: application/json")
    @GET("/trainer")
    suspend fun fetchProfileData(): Response<TrainerResponse>

    @Headers("Content-Type: application/json")
    @GET("/friends")
    suspend fun fetchFriendsData(): Response<FriendsResponse>

    @Headers("Content-Type: application/json")
    @POST("/remove_friend")
    suspend fun removeFriend(@Body data: FriendshipCodeRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/add_friend")
    suspend fun addFriend(@Body data: FriendshipCodeRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/register_users")
    suspend fun registerUsers(@Body data: List<UserRequest>): Response<Unit>

}