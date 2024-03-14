package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.BodyFatState

data class BodyFatRequest(
    val height: String,
    val gender: String,
    val image: String
)

data class BodyFatResponse(
    @SerializedName("body_fat")
    val bodyFat: Int,
    @SerializedName("category")
    val category: String,
)

fun BodyFatResponse.toUiModel() = BodyFatState(
    bodyFat = bodyFat,
    category = category
)