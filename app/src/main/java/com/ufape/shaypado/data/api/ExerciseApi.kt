package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.ExerciseResponse
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface ExerciseApi {

    @Headers("Content-Type: application/json")
    @POST("/exercises")
    suspend fun createExercise(@Body data: CreateExerciseRequest): Response<ExerciseResponse>


    @Headers("Content-Type: application/json")
    @PUT("/exercises")
    suspend fun updateExercise(@Body data: UpdateExerciseRequest): Response<ExerciseResponse>
}

