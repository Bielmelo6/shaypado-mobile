package com.ufape.shaypado.data.repositories.implementations

import com.ufape.shaypado.data.api.AuthApi
import com.ufape.shaypado.data.model.LoginData
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.UserData
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.util.Result

class AuthRepository(
    private val api: AuthApi
): IAuthRepository {
    override suspend fun login(data: LoginData): Result<LoginResponse> {
        val res = api.login(data)
        return if(res.isSuccessful){
            Result.Success(res.body()!!)
        }else{
            Result.Error(Exception())
        }
    }

    override suspend fun register(data: UserData): Result<LoginResponse> {
        val res = api.register(data)
        return if(res.isSuccessful){
            Result.Success(res.body()!!)
        }else{
            Result.Error(Exception())
        }
    }
}