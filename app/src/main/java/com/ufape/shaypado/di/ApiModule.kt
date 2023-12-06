package com.ufape.shaypado.di

import com.ufape.shaypado.data.api.AuthApi
import com.ufape.shaypado.data.model.LoginData
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.UserResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    //ToDo: Provide real api
    @Singleton
    @Provides
    fun provideAuthApi(): AuthApi = object : AuthApi {
        override suspend fun login(data: LoginData): Response<LoginResponse> {
            return if (data.email == "teste@email.com" && data.password == "123456") {
                Response.success(200, LoginResponse("token", UserResponse("Usuário de Teste")))
            } else {
                return Response.error(
                    401,
                    "{\"error\":[\"wrong credentials\"]}"
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
            }
        }
    }
}