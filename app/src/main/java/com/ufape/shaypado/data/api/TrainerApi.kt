package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.TrainerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface TrainerApi {
    @Headers("Content-Type: application/json")
    @GET("/trainer")
    suspend fun fetchProfileData(): Response<TrainerResponse>
}