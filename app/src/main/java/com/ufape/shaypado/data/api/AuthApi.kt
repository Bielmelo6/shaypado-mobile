package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.LoginRequest
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.TrainerRequest
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("/users/login")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("/users/register")
    suspend fun registerUser(@Body data: UserRequest): Response<Unit>

    @Multipart
    @POST("/users/registerTrainer")
    suspend fun registerTrainer(
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("userType") userType: RequestBody,
        @Part("full_name") fullName: RequestBody,
        @Part("contact_email") contactEmail: RequestBody,
        @Part("contact_phone") contactPhone: RequestBody,
        @Part("specialties") specialties: RequestBody,
        @Part("age") age: RequestBody,
        @Part("state") state: RequestBody,
        @Part("city") city: RequestBody,
        @Part("work_location") workLocation: RequestBody?,
        @Part profilePicture: MultipartBody.Part?,
        @Part plansDocument: MultipartBody.Part,
    ): Response<Unit>
}