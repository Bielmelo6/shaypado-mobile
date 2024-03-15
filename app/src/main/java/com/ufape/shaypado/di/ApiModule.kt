package com.ufape.shaypado.di

import com.ufape.shaypado.data.AuthInterceptor
import com.ufape.shaypado.data.TokenAuthenticator
import com.ufape.shaypado.data.api.AuthApi
import com.ufape.shaypado.data.api.ClassApi
import com.ufape.shaypado.data.api.ExerciseApi
import com.ufape.shaypado.data.api.FriendApi
import com.ufape.shaypado.data.api.IaApi
import com.ufape.shaypado.data.api.TrainerApi
import com.ufape.shaypado.data.api.UserApi
import com.ufape.shaypado.data.api.WorkoutApi
import com.ufape.shaypado.data.local.ISessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    val BASE_URL = "http://192.168.1.111:3001"
    val IA_BASE_URL = "https://3a6f-2804-29b8-50ba-608-39ab-d1b3-5734-d935.ngrok-free.app/"

    @Singleton
    @Provides
    fun provideAuthInterceptor(manager: ISessionManager) = AuthInterceptor(manager)

    @Singleton
    @Provides
    fun provideTokenAuth(
        sessionManager: ISessionManager,
    ): TokenAuthenticator =
        TokenAuthenticator(sessionManager)

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenAuthenticator: TokenAuthenticator,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .callTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Singleton
    @Provides
    fun provideTrainerApi(retrofit: Retrofit): TrainerApi = retrofit.create(TrainerApi::class.java)

    @Singleton
    @Provides
    fun provideExerciseApi(retrofit: Retrofit): ExerciseApi =
        retrofit.create(ExerciseApi::class.java)

    @Singleton
    @Provides
    fun provideClassApi(retrofit: Retrofit): ClassApi = retrofit.create(ClassApi::class.java)

    @Singleton
    @Provides
    fun provideWorkoutApi(retrofit: Retrofit): WorkoutApi = retrofit.create(WorkoutApi::class.java)

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Singleton
    @Provides
    fun friendApi(retrofit: Retrofit): FriendApi = retrofit.create(FriendApi::class.java)

    @Singleton
    @Provides
    fun provideIaApi(): IaApi {

        val okHttpClient =  OkHttpClient
            .Builder()
            .callTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(IA_BASE_URL)
            .client(okHttpClient)
            .build()

        return retrofit.create(IaApi::class.java)
    }


}