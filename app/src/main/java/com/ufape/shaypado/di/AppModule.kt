package com.ufape.shaypado.di

import android.content.Context
import com.ufape.shaypado.util.INetworkValidator
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.NetworkHelper
import com.ufape.shaypado.util.NetworkValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNetValidator(@ApplicationContext context: Context): INetworkValidator =
        NetworkValidator(context)

    @Provides
    @Singleton
    fun providesNetHelper(validator: INetworkValidator): ISafeNetworkHandler =
        NetworkHelper(validator)



}