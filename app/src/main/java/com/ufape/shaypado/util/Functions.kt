package com.ufape.shaypado.util

import android.content.Context
import com.google.gson.Gson
import com.ufape.shaypado.R
import com.ufape.shaypado.data.model.ApiError
import com.ufape.shaypado.exceptions.ApiErrorException
import retrofit2.Response

fun Response<*>.getApiError(): ApiErrorException? {
    val response = this
    return if (!response.isSuccessful) {
        try {
            val converted = Gson().fromJson(response.errorBody()?.string(), ApiError::class.java)
            ApiErrorException(
                fieldName = converted.fieldName,
                message = converted.message
            )
        } catch (e: Exception) {
            null
        }
    } else {
        null
    }
}




fun Exception.getErrorMessage(context: Context): String {
    return when (this) {
        is ApiErrorException -> this.message ?: context.getString(R.string.unknown_error)
        is NoNetworkException -> context.getString(this.messageRes)
        else -> context.getString(R.string.unknown_error)
    }
}
