package com.ufape.shaypado.util

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.ufape.shaypado.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import java.io.IOException
import java.lang.Exception

interface ISafeNetworkHandler {
    suspend fun <T : Any> makeSafeApiCall(
        apiFunction: suspend () -> kotlin.Result<T>
    ): kotlin.Result<T>
}

class NoNetworkException(
    @StringRes val messageRes: Int = R.string.no_network
) : Exception()


class NetworkHelper(
    private val networkValidator: INetworkValidator,
) : ISafeNetworkHandler {

    override suspend fun <T : Any> makeSafeApiCall(apiFunction: suspend () -> kotlin.Result<T>): kotlin.Result<T> {
        val result: kotlin.Result<T> = try {
            if (networkValidator.hasInternetConnection()) {
                (Dispatchers.IO){ apiFunction() }
            } else {
                kotlin.Result.failure(NoNetworkException())
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    kotlin.Result.failure(NoNetworkException())
                }

                else -> {
                    kotlin.Result.failure(Exception(t))
                }
            }
        }
        return (Dispatchers.Main) { result }
    }
}