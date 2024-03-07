package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.ClassResponse
import com.ufape.shaypado.data.model.CreateClassRequest
import com.ufape.shaypado.data.model.UpdateClassRequest
import com.ufape.shaypado.ui.model.ClassState
import com.ufape.shaypado.util.Result

interface IClassRepository {
    suspend fun addClasses(classes: List<CreateClassRequest>) : Result<Unit>

    suspend fun updateClass(id : String, classData : UpdateClassRequest) : Result<ClassState>

    suspend fun deleteClass(deleteClass : String) : Result<Unit>

    suspend fun getClasses() : Result<List<ClassState>>

    suspend fun getClass(id : String) : Result<ClassState>
}