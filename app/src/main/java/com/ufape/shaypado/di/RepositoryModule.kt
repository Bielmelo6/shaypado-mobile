package com.ufape.shaypado.di

import com.ufape.shaypado.data.api.AuthApi
import com.ufape.shaypado.data.api.ClassApi
import com.ufape.shaypado.data.api.ExerciseApi
import com.ufape.shaypado.data.api.FriendApi
import com.ufape.shaypado.data.api.IaApi
import com.ufape.shaypado.data.api.TrainerApi
import com.ufape.shaypado.data.api.WorkoutApi
import com.ufape.shaypado.data.local.ISessionManager
import com.ufape.shaypado.data.repositories.implementations.AuthRepository
import com.ufape.shaypado.data.repositories.implementations.ClassRepository
import com.ufape.shaypado.data.repositories.implementations.ExerciseRepository
import com.ufape.shaypado.data.repositories.implementations.FriendRepository
import com.ufape.shaypado.data.repositories.implementations.IaRepository
import com.ufape.shaypado.data.repositories.implementations.TrainerRepository
import com.ufape.shaypado.data.repositories.implementations.WorkoutRepository
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.data.repositories.interfaces.IClassRepository
import com.ufape.shaypado.data.repositories.interfaces.IExerciseRepository
import com.ufape.shaypado.data.repositories.interfaces.IFriendRepository
import com.ufape.shaypado.data.repositories.interfaces.IIaRepository
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.data.repositories.interfaces.IWorkoutRepository
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

    @Singleton
    @Provides
    fun provideClassRepository(
        api: ClassApi
    ): IClassRepository = ClassRepository(api)

    @Singleton
    @Provides
    fun provideFriendRepository(
        api: FriendApi
    ): IFriendRepository = FriendRepository(api)

    @Singleton
    @Provides
    fun provideWorkoutRepository(
        api: WorkoutApi
    ): IWorkoutRepository = WorkoutRepository(api)

    @Singleton
    @Provides
    fun provideExerciseRepository(
        api: ExerciseApi
    ): IExerciseRepository = ExerciseRepository(api)

    @Singleton
    @Provides
    fun provideIaRepository(
        api: IaApi
    ): IIaRepository = IaRepository(api)



}