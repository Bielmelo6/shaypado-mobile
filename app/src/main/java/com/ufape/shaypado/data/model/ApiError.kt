package com.ufape.shaypado.data.model

data class ApiError(
    val fieldName: String?,
    val message: String?,
    val error: String?
)