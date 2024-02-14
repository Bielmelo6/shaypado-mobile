package com.ufape.shaypado.data

import com.ufape.shaypado.data.local.ISessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sessionManager: ISessionManager) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}