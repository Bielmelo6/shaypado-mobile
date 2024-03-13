package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.UpdatePhysicalFormRequest
import com.ufape.shaypado.data.model.TrainerResponse
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.UserResponseWithWorkouts
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TrainerApi {
    @Headers("Content-Type: application/json")
    @GET("/trainer_profile")
    suspend fun fetchProfileData(): Response<TrainerResponse>

    @Headers("Content-Type: application/json")
    @POST("/register_users")
    suspend fun registerUsers(@Body data: List<UserRequest>): Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/trainer_profile/user/{id}")
    suspend fun fetchStudent(@Path("id") id: String): Response<UserResponseWithWorkouts>

    @Headers("Content-Type: application/json")
    @PUT("/trainer_profile/user/{id}")
    suspend fun updatePhysicalForm(@Path("id") id: String, @Body data : UpdatePhysicalFormRequest): Response<UserResponseWithWorkouts>
}