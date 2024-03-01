package com.ufape.shaypado.di

import com.ufape.shaypado.data.api.AuthApi
import com.ufape.shaypado.data.api.TrainerApi
import com.ufape.shaypado.data.local.ISessionManager
import com.ufape.shaypado.data.repositories.implementations.AuthRepository
import com.ufape.shaypado.data.repositories.implementations.TrainerRepository
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        api: AuthApi,
        sessionManager: ISessionManager
    ): IAuthRepository = AuthRepository(api, sessionManager)

    @Singleton
    @Provides
    fun provideTrainerRepository(
        api: TrainerApi
    ): ITrainerRepository = TrainerRepository(api)
}