package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.WorkoutIdRequest
import com.ufape.shaypado.data.model.WorkoutRequest
import com.ufape.shaypado.ui.model.FriendsData
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.ui.model.WorkoutData
import com.ufape.shaypado.util.Result

interface ITrainerRepository {
    suspend fun fetchTrainerProfile(): Result<TrainerProfileData>
    suspend fun fetchFriends(): Result<FriendsData>

    suspend fun removeFriend(friendshipCode: FriendshipCodeRequest): Result<Unit>
    suspend fun addFriend(friendshipCode: FriendshipCodeRequest): Result<Unit>
    suspend fun registerUsers(users: List<UserRequest>): Result<Unit>
    suspend fun createTraining(profile: List<WorkoutRequest>): Result<Unit>
    suspend fun fetchWorkouts(): Result<List<WorkoutData>>

    suspend fun deleteWorkout(workoutId: WorkoutIdRequest): Result<Unit>

    suspend fun fetchWorkout(workoutId: String): Result<WorkoutData>

}