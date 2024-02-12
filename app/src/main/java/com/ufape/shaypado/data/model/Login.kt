package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.LoginData

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
    return LoginData(
        token = this.token,
        isFirstLogin = true,
        isEmailValid = true,
        userType = "user"
    )
}