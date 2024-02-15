package com.ufape.shaypado.util

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val stringRes: Int,
        vararg val args: Any
    ) : UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(stringRes, *args)
        }
    }

    fun getStringRes(): Int? {
        return when (this) {
            is DynamicString -> null
            is StringResource -> stringRes
        }
    }
}