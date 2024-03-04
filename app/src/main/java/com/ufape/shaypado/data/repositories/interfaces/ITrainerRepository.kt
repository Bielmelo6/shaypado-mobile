package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.FriendshipCodeRequest
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.WorkoutIdRequest
import com.ufape.shaypado.data.model.CreateWorkoutRequest
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.ui.model.ExerciseData
import com.ufape.shaypado.ui.model.FriendsData
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.ui.model.WorkoutData
import com.ufape.shaypado.ui.screens.trainer.updateWorkout.CategoriesState
import com.ufape.shaypado.util.Result

interface ITrainerRepository {
    suspend fun fetchTrainerProfile(): Result<TrainerProfileData>
    suspend fun registerUsers(users: List<UserRequest>): Result<Unit>
}