package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.ui.model.UserState
import com.ufape.shaypado.ui.model.UserWithWorkoutState
import com.ufape.shaypado.util.Result

interface ITrainerRepository {
    suspend fun fetchTrainerProfile(): Result<TrainerProfileData>
    suspend fun registerUsers(users: List<UserRequest>): Result<Unit>
    suspend fun fetchStudent(id: String): Result<UserWithWorkoutState>
}