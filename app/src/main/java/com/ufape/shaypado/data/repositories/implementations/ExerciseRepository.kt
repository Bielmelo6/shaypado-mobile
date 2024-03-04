package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.ExerciseApi
import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.ExerciseResponse
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import com.ufape.shaypado.data.repositories.interfaces.IExerciseRepository
import com.ufape.shaypado.util.getApiError

class ExerciseRepository (
    private val api : ExerciseApi
) : IExerciseRepository {
    override suspend fun addExercise(addExerciseRequest: CreateExerciseRequest): Result<ExerciseResponse> {
        val result = api.createExercise(addExerciseRequest)
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(result.getApiError())
        }
    }

    override suspend fun updateExercise(updateExerciseRequest: UpdateExerciseRequest): Result<ExerciseResponse> {
        val result = api.updateExercise(updateExerciseRequest)
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(result.getApiError())
        }
    }
}