package com.ufape.shaypado.di

import android.content.Context
import android.content.SharedPreferences
import com.ufape.shaypado.R
import com.ufape.shaypado.data.local.ISessionManager
import com.ufape.shaypado.data.local.SessionManager
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

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesNetHelper(validator: INetworkValidator): ISafeNetworkHandler =
        NetworkHelper(validator)


    @Provides
    @Singleton
    fun provideSessionManager(preferences: SharedPreferences): ISessionManager =
        SessionManager(preferences)

}