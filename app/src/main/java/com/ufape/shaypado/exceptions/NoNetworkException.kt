package com.ufape.shaypado.exceptions

import androidx.annotation.StringRes
import com.ufape.shaypado.R
import java.lang.Exception

class NoNetworkException(
    @StringRes val messageRes: Int = R.string.no_network
) : Exception()