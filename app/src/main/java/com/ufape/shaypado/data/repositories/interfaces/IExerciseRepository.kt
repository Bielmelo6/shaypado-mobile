package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.ExerciseResponse
import com.ufape.shaypado.data.model.UpdateExerciseRequest

interface IExerciseRepository {
    suspend fun addExercise(
        addExerciseRequest: CreateExerciseRequest
    ): Result<ExerciseResponse>

    suspend fun updateExercise(updateExerciseRequest: UpdateExerciseRequest): Result<ExerciseResponse>
}