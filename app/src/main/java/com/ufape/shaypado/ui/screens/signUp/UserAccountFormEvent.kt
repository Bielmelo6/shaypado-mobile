package com.ufape.shaypado.ui.screens.signUp

sealed class UserAccountFormEvent {
    data class OnNameChanged(val name: String) : UserAccountFormEvent()
    data class OnEmailChanged(val email: String) : UserAccountFormEvent()
    data class OnEmailConfirmationChanged(val emailConfirmation: String) : UserAccountFormEvent()
    data class OnPasswordChanged(val password: String) : UserAccountFormEvent()
    data class OnPasswordConfirmationChanged(val passwordConfirmation: String) : UserAccountFormEvent()
    data object OnSubmit : UserAccountFormEvent()
}
