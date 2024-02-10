package com.ufape.shaypado.ui.screens.login

import androidx.annotation.StringRes

data class LoginFormState(
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null,
)