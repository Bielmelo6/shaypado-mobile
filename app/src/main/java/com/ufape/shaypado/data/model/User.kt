package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.UserData

data class UserRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("userType")
    val userType: String,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("objective")
    val objective : String?,
    @SerializedName("any_disease")
    val anyDisease : Boolean
)

data class UserResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("userType")
    val userType: String,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("objective")
    val objective : String?,
    @SerializedName("any_disease")
    val anyDisease : Boolean
)

fun UserResponse.toUiModel(): UserData {
    return UserData(
        name = this.name,
        email = this.email,
        userType = this.userType,
        weight = this.weight,
        height = this.height,
        objective = this.objective,
        anyDisease = this.anyDisease
    )
}


