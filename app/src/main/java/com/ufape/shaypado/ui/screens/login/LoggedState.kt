package com.ufape.shaypado.ui.screens.login

import com.ufape.shaypado.ui.model.UserType

data class LoggedState(
    val token: String? = null,
    val userType : UserType? = null,
    val isEmailValid : Boolean = false
)
