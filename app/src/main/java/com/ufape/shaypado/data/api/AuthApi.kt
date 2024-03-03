package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.LoginRequest
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.TrainerRequest
import com.ufape.shaypado.data.model.UploadResponse
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("/login")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/users/register")
    suspend fun registerUser(@Body data: UserRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/trainer_profile")
    suspend fun registerTrainer(@Body data: TrainerRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @PUT("/update_trainer")
    suspend fun updateTrainer(@Body data: TrainerRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @PUT("/update_user")
    suspend fun updateUser(@Body data: UserRequest): Response<Unit>

    @Multipart
    @POST("/trainer_profile/upload_picture")
    suspend fun uploadProfilePicture(
        @Part profilePicture: MultipartBody.Part,
    ): Response<UploadResponse>

    @Multipart
    @POST("/trainer_profile/upload_document")
    suspend fun uploadPlansDocument(
        @Part plansDocument: MultipartBody.Part,
    ): Response<UploadResponse>
}