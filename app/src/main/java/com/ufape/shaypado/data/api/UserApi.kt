package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.WorkoutResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {
    @Headers("Content-Type: application/json")
    @GET("/workouts_user")
    suspend fun fetchWorkouts(): Response<List<WorkoutResponse>>

    suspend fun fetchWorkout(

    )

    suspend fun concludeWorkout()
    suspend fun concludeExercise()

    suspend fun fetchProfessionals()

    suspend fun fetchProfessional()
}