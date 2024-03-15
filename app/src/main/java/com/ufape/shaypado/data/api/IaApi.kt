package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.BodyFatResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface IaApi {
    @Multipart
    @POST("body-fat")
    suspend fun fetchBodyFat(
        @Query("height") height: Int,
        @Query("gender") gender: String,
        @Part image: MultipartBody.Part,
    ): Response<BodyFatResponse>
}