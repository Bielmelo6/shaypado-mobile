package com.ufape.shaypado.ui.model

enum class UserType(
    val value: String
) {
    USER("student"),
    TRAINER("teacher")
}

data class LoginData(
    val token : String,
    val userType : UserType,
)
