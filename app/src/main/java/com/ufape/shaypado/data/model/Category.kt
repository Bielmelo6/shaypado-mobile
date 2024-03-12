package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.CategoryState

data class CategoryResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val category: String
)

fun CategoryResponse.toUiModel() = CategoryState(
    id = id,
    category = category
)