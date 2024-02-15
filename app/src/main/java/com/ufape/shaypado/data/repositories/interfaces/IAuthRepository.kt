package com.ufape.shaypado.data.repositories.interfaces

import com.ufape.shaypado.data.model.LoginRequest
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.ui.model.LoginData
import com.ufape.shaypado.ui.model.UserData
import com.ufape.shaypado.util.Result

interface IAuthRepository {
    suspend fun login(data: LoginRequest): Result<LoginData>
    suspend fun register(data: UserRequest): Result<UserData>
    fun logout()
    fun saveUser(user: LoginResponse)

    fun fetchUser(): LoginData?

    fun fetchAuthToken(): String?

}