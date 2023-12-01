package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.LoginData
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.util.Result

interface IAuthRepository {
    suspend fun login(data: LoginData): Result<LoginResponse>

}