package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.LoginData
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("/session")
    suspend fun login(@Body data: LoginData): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/users/register")
    suspend fun register(@Body data: UserData): Response<LoginResponse>
}