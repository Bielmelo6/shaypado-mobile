package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.ClassApi
import com.ufape.shaypado.data.model.CreateClassRequest
import com.ufape.shaypado.data.model.UpdateClassRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IClassRepository
import com.ufape.shaypado.ui.model.ClassState
import com.ufape.shaypado.util.getApiError
import com.ufape.shaypado.util.Result

class ClassRepository(
    private val api: ClassApi
) : IClassRepository {
    override suspend fun addClasses(classes: List<CreateClassRequest>): Result<Unit> {
        val result = api.addClasses(classes)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun updateClass(id : String, classData: UpdateClassRequest): Result<ClassState> {
        val result = api.updateClass(id, classData)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun deleteClass(deleteClass: String): Result<Unit> {
        val result = api.deleteClass(deleteClass)
        return if (result.isSuccessful) {
            Result.Success(Unit)
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun getClasses(): Result<List<ClassState>> {
        val result = api.getClasses()
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.map { it.toUiModel() })
        } else {
            Result.Error(result.getApiError())
        }
    }

    override suspend fun getClass(id: String): Result<ClassState> {
        val result = api.getClass(id)
        return if (result.isSuccessful) {
            Result.Success(result.body()!!.toUiModel())
        } else {
            Result.Error(result.getApiError())
        }
    }
}