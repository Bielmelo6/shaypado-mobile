package com.ufape.shaypado.data.api

import com.ufape.shaypado.data.model.CategoryResponse
import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.ExerciseResponse
import com.ufape.shaypado.data.model.FriendsResponse
import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.TrainerResponse
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.WorkoutIdRequest
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import com.ufape.shaypado.data.model.WorkoutResponse
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TrainerApi {
    @Headers("Content-Type: application/json")
    @GET("/trainer_profile")
    suspend fun fetchProfileData(): Response<TrainerResponse>

    @Headers("Content-Type: application/json")
    @GET("/friends")
    suspend fun fetchFriendsData(): Response<FriendsResponse>

    @Headers("Content-Type: application/json")
    @POST("/remove_friend")
    suspend fun removeFriend(@Body data: FriendshipCodeRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/add_friend")
    suspend fun addFriend(@Body data: FriendshipCodeRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/register_users")
    suspend fun registerUsers(@Body data: List<UserRequest>): Response<Unit>

    @Headers("Content-Type: application/json")
    @POST("/create_training")
    suspend fun createTraining(@Body data: List<CreateWorkoutRequest>): Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/workouts")
    suspend fun fetchTrainings(): Response<List<WorkoutResponse>>

    @Headers("Content-Type: application/json")
    @DELETE("/delete_training")
    suspend fun deleteWorkout(@Body data: WorkoutIdRequest): Response<Unit>

    @Headers("Content-Type: application/json")
    @GET("/workout")
    suspend fun fetchWorkout(@Query("workout_id") workoutId: String): Response<WorkoutResponse>

    @Headers("Content-Type: application/json")
    @GET("/fetch_categories")
    suspend fun fetchCategories(): Response<List<CategoryResponse>>


    @Headers("Content-Type: application/json")
    @POST("/create_exercise")
    suspend fun createExercise(@Body data: CreateExerciseRequest): Response<ExerciseResponse>


    @Headers("Content-Type: application/json")
    @POST("/update_exercise")
    suspend fun updateExercise(@Body data: UpdateExerciseRequest): Response<ExerciseResponse>


    @Headers("Content-Type: application/json")
    @POST("/update_training")
    suspend fun updateWorkout(@Body data: UpdateWorkoutRequest): Response<WorkoutResponse>
}