package com.ufape.shaypado.ui.screens.login

data class LoggedState(
    val token: String = "",
    val userType : String = "",
    val isLogged: Boolean = false,
    val isTokenValid : Boolean = false,
)
