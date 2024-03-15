package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.WorkoutResponse
import com.ufape.shaypado.util.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @Headers("Content-Type: application/json")
    @GET("/workouts_user")
    suspend fun fetchWorkouts(): Response<List<WorkoutResponse>>

    @Headers("Content-Type: application/json")
    @POST("/workouts_user/conclude/{id}")
    suspend fun concludeWorkout(@Query("id") id : String) : Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/workouts_user/undo/{id}")
    suspend fun undoWorkout(@Query("id") id : String) : Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/workouts_user/exercise/conclude/{id}")
    suspend fun concludeExercise(@Query("id") id : String) : Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/workouts_user/exercise/undo/{id}")
    suspend fun undoExercise(@Query("id") id : String) : Response<Unit>

    suspend fun fetchProfessionals()

    suspend fun fetchProfessional()
}