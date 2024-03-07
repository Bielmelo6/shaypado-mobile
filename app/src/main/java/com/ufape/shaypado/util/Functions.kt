package com.ufape.shaypado.util

import android.content.Context
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.ufape.shaypado.R
import com.ufape.shaypado.data.model.ApiError
import com.ufape.shaypado.exceptions.ApiErrorException
import com.ufape.shaypado.exceptions.UiTextException
import retrofit2.Response
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Response<*>.getApiError(): Exception {
    val response = this
    return if (!response.isSuccessful) {
        try {
            val converted = Gson().fromJson(response.errorBody()?.string(), ApiError::class.java)
            ApiErrorException(
                fieldName = converted.fieldName,
                message = converted.message ?: converted.error,
            )
        } catch (e: Exception) {
            Exception()
        }
    } else {
        Exception()
    }
}


fun Exception.getErrorMessage(context: Context): String {
    return when (this) {
        is ApiErrorException -> this.message ?: context.getString(R.string.unknown_error)
        is NoNetworkException -> context.getString(this.messageRes)
        is UiTextException -> this.errorMessage.asString(context)
        else -> context.getString(R.string.unknown_error) + " ${this.message}"
    }
}

fun Context.copyToClipboard(label: String, text: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = android.content.ClipData.newPlainText(label, text)
    clipboard.setPrimaryClip(clip)
}

@Composable
fun Modifier.shimmerEffect(isShimerring: Boolean = true): Modifier {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transaction = rememberInfiniteTransition(label = "")
    val startOffSet by transaction.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
        ),
        label = ""
    )

    if (isShimerring) {
        return this then Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFE6E6E6),
                        Color(0xFFB9B9B9),
                        Color(0xFFE6E6E6)
                    ),
                    start = Offset(startOffSet, 0f),
                    end = Offset(startOffSet + size.width.toFloat(), size.height.toFloat())
                )
            )
            .onGloballyPositioned {
                size = it.size
            }
    }
    return this
}