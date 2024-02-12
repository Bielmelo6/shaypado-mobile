package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.AuthApi
import com.ufape.shaypado.data.local.ISessionManager
import com.ufape.shaypado.data.model.LoginRequest
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.model.toUiModel
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.ui.model.LoginData
import com.ufape.shaypado.ui.model.UserData
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getApiError

class AuthRepository(
    private val api: AuthApi,
    private val local: ISessionManager
) : IAuthRepository {
    override suspend fun login(data: LoginRequest): Result<LoginData> {
        val res = api.login(data)
        return if (res.isSuccessful) {
            saveUser(res.body()!!)
            Result.Success(res.body()!!.toUiModel())
        } else {
            Result.Error(res.getApiError())
        }
    }

    override suspend fun register(data: UserRequest): Result<UserData> {
        val res = api.register(data)
        return if (res.isSuccessful) {
            Result.Success(res.body()!!.toUiModel())
        } else {
            Result.Error(Exception())
        }
    }

    override fun logout() {
        local.removeUser()
    }

    override fun saveUser(user: LoginResponse) {
        local.saveUser(user)
    }

    override fun fetchUser(): LoginResponse? {
        return local.fetchUser()
    }

    override fun fetchAuthToken(): String? {
        return local.fetchAuthToken()
    }
}