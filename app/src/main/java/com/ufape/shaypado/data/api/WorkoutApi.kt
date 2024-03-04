package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.CategoryResponse
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.model.WorkoutResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface WorkoutApi {
    @Headers("Content-Type: application/json")
    @GET("/workouts")
    suspend fun getWorkouts(): Response<List<WorkoutResponse>>

    @Headers("Content-Type: application/json")
    @DELETE("/delete_training")
    suspend fun deleteWorkout(@Path("id") id : String): Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/workout")
    suspend fun getWorkout(@Query("id") id: String): Response<WorkoutResponse>

    @Headers("Content-Type: application/json")
    @POST("/create_training")
    suspend fun createWorkouts(@Body data: List<CreateWorkoutRequest>): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/update_training")
    suspend fun updateWorkout(@Body data: UpdateWorkoutRequest): Response<WorkoutResponse>

    @Headers("Content-Type: application/json")
    @GET("/fetch_categories")
    suspend fun fetchWorkoutCategories(): Response<List<CategoryResponse>>
}