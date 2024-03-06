package com.ufape.shaypado.data.model

data class BodyFatRequest(
    val height: String,
    val gender: String,
    val image: String
)

data class BodyFatResponse(
    val id: String,
    val height: String,
)