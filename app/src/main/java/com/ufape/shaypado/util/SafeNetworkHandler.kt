package com.ufape.shaypado.util

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.MutableLiveData
import com.ufape.shaypado.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import java.io.IOException
import java.lang.Exception

interface ISafeNetworkHandler {
    suspend fun <T : Any> makeSafeApiCall(
        outputLiveData: MutableLiveData<Result<T>>,
        apiFunction: suspend () -> Result<T>,
    )

    suspend fun <T : Any> makeSafeApiCall(
        apiFunction: suspend () -> Result<T>
    ): Result<T>

}

class NoNetworkException(
    @StringRes val messageRes: Int = R.string.no_network
) : Exception()


class NetworkHelper(
    private val networkValidator: INetworkValidator,
) : ISafeNetworkHandler {
    override suspend fun <T : Any> makeSafeApiCall(
        outputLiveData: MutableLiveData<Result<T>>,
        apiFunction: suspend () -> Result<T>
    ) {
        outputLiveData.postValue(makeSafeApiCall(apiFunction))
    }

    override suspend fun <T : Any> makeSafeApiCall(apiFunction: suspend () -> Result<T>): Result<T> {
        val result: Result<T> = try {
            if (networkValidator.hasInternetConnection()) {
                (Dispatchers.IO){ apiFunction() }
            } else {
                Result.Error(NoNetworkException())
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    Result.Error(NoNetworkException())
                }

                else -> {
                    Result.Error(Exception(t))
                }
            }
        }
        return (Dispatchers.Main) { result }
    }
}