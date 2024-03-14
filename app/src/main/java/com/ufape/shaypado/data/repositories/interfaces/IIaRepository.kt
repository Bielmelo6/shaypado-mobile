package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.BodyFatRequest
import com.ufape.shaypado.ui.model.BodyFatState
import com.ufape.shaypado.util.Result


interface IIaRepository {

    suspend fun fetchBodyFat (request : BodyFatRequest) : Result<BodyFatState>
}