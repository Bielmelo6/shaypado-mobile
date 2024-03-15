package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.ExerciseApi
import com.ufape.shaypado.data.model.CreateExerciseRequest
import com.ufape.shaypado.data.model.ExerciseResponse
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IExerciseRepository
import com.ufape.shaypado.ui.model.ExerciseState
import com.ufape.shaypado.util.getApiError
import com.ufape.shaypado.util.Result

class ExerciseRepository(
    private val api: ExerciseApi
) : IExerciseRepository {
    override suspend fun addExercise(addExerciseRequest: CreateExerciseRequest): Result<ExerciseState> {
        val result = api.createExercise(addExerciseRequest)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun updateExercise(updateExerciseRequest: UpdateExerciseRequest): Result<ExerciseState> {
        val result = api.updateExercise(updateExerciseRequest)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun fetchExercise(id: String): Result<ExerciseState> {
        val result = api.fetchExercise(id)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }
}