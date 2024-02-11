package com.ufape.shaypado.ui.screens.login

sealed class LoginFormEvent {
    data class OnEmailChanged(val email: String) : LoginFormEvent()
    data class OnPasswordChanged(val password: String) : LoginFormEvent()
    data object OnSubmit : LoginFormEvent()
}