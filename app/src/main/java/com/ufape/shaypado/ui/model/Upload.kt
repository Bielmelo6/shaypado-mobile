package com.ufape.shaypado.ui.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.UploadData

data class UploadResponse(
    @SerializedName("id")
    val id: String
)

fun UploadResponse.toUiModel(): UploadData {
    return UploadData(
        id = this.id
    )
}