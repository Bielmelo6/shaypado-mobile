package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: UserResponse,
)

data class UserResponse(
    @SerializedName("name")
    val name: String
)
