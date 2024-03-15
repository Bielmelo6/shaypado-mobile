package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.ExerciseResponse
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import com.ufape.shaypado.ui.model.ExerciseState
import com.ufape.shaypado.util.Result

interface IExerciseRepository {
    suspend fun addExercise(
        addExerciseRequest: CreateExerciseRequest
    ): Result<ExerciseState>

    suspend fun updateExercise(updateExerciseRequest: UpdateExerciseRequest): Result<ExerciseState>
    suspend fun fetchExercise(id: String): Result<ExerciseState>
}