package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.LoginRequest
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("/session")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/users/register")
    suspend fun register(@Body data: UserRequest): Response<UserResponse>
}