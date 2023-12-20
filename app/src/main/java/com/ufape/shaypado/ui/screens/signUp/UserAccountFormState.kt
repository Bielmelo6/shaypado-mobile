package com.ufape.shaypado.ui.screens.signUp

data class UserAccountFormState(
    val userType : String = "",
    val userTypeError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val emailConfirmation: String = "",
    val emailConfirmationError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val passwordConfirmation: String = "",
    val passwordConfirmationError: String? = null,
    val workoutType: String = "",
    val workoutTypeError: String? = null,
)
