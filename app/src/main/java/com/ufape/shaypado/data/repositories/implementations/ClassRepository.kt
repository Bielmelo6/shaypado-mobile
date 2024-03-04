package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.ClassApi
import com.ufape.shaypado.data.model.ClassResponse
import com.ufape.shaypado.data.model.CreateClassRequest
import com.ufape.shaypado.data.model.UpdateClassRequest
import com.ufape.shaypado.data.repositories.interfaces.IClassRepository
import com.ufape.shaypado.util.getApiError

class ClassRepository(
    private val api: ClassApi
) : IClassRepository {
    override suspend fun addClasses(classes: List<CreateClassRequest>): Result<Unit> {
        val result = api.addClasses(classes)
        return if (result.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(result.getApiError())
        }
    }

    override suspend fun updateClass(classData: UpdateClassRequest): Result<ClassResponse> {
        val result = api.updateClass(classData)
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(result.getApiError())
        }
    }

    override suspend fun deleteClass(deleteClass: String): Result<Unit> {
        val result = api.deleteClass(deleteClass)
        return if (result.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(result.getApiError())
        }
    }

    override suspend fun getClasses(): Result<List<ClassResponse>> {
        val result = api.getClasses()
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(result.getApiError())
        }
    }

    override suspend fun getClass(id: String): Result<ClassResponse> {
        val result = api.getClass(id)
        return if (result.isSuccessful) {
            Result.success(result.body()!!)
        } else {
            Result.failure(result.getApiError())
        }
    }
}