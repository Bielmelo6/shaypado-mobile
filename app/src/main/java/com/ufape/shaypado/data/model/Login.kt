package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.LoginData
import com.ufape.shaypado.ui.model.UserType

data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: UserResponse,
)

fun LoginResponse.toUiModel(): LoginData {
    val userType = when (user.userType) {
        "student" -> UserType.USER
        "trainer" -> UserType.TRAINER
        else -> throw IllegalArgumentException("Unknown user type")
    }

    return LoginData(
        token = this.token,
        userType = userType
    )
}