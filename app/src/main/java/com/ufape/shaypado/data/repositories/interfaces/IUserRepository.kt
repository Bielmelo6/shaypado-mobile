package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.Result


interface IUserRepository {
    suspend fun fetchWorkouts() : Result<List<WorkoutState>>
    suspend fun fetchWorkout(id : String) : Result<WorkoutState>

    suspend fun concludeWorkout()
    suspend fun concludeExercise()

    suspend fun fetchProfessionals()

    suspend fun fetchProfessional()
}