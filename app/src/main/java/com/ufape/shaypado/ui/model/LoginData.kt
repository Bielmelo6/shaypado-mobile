package com.ufape.shaypado.ui.model

data class LoginData(
    val token : String,
    val isFirstLogin : Boolean,
    val isEmailValid : Boolean,
    val userType : String,
)
