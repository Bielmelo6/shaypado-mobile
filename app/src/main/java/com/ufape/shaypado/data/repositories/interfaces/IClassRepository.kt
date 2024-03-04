package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.ClassResponse
import com.ufape.shaypado.data.model.CreateClassRequest
import com.ufape.shaypado.data.model.UpdateClassRequest

interface IClassRepository {
    suspend fun addClasses(classes: List<CreateClassRequest>) : Result<Unit>

    suspend fun updateClass(classData : UpdateClassRequest) : Result<ClassResponse>

    suspend fun deleteClass(deleteClass : String) : Result<Unit>

    suspend fun getClasses() : Result<List<ClassResponse>>

    suspend fun getClass(id : String) : Result<ClassResponse>
}