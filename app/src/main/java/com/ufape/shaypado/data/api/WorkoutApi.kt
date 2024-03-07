package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.CategoryResponse
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.model.WorkoutResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface WorkoutApi {
    @Headers("Content-Type: application/json")
    @GET("/workouts")
    suspend fun getWorkouts(): Response<List<WorkoutResponse>>

    @Headers("Content-Type: application/json")
    @DELETE("/workouts/{id}")
    suspend fun deleteWorkout(@Path("id") id : String): Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/workouts/workout")
    suspend fun getWorkout(@Query("id") id: String): Response<WorkoutResponse>

    @Headers("Content-Type: application/json")
    @POST("/workouts//multiple-create-workouts")
    suspend fun createWorkouts(@Body data: List<CreateWorkoutRequest>): Response<Unit>

    @Headers("Content-Type: application/json")
    @PUT("/workouts/update_workout")
    suspend fun updateWorkout(@Body data: UpdateWorkoutRequest): Response<WorkoutResponse>

    @Headers("Content-Type: application/json")
    @GET("/workouts/workout-types")
    suspend fun fetchWorkoutCategories(): Response<List<CategoryResponse>>

    @Multipart
    @POST("/body-fat")
    suspend fun uploadBodyFat(
        @Part("height") height: RequestBody,
        @Part("gender") gender: RequestBody,
        @Query("image") image: MultipartBody.Part,
    ): Response<Unit>

}