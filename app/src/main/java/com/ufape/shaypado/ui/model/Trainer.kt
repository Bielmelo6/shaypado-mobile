package com.ufape.shaypado.ui.model

data class TrainerState(
    val name: String,
    val email: String,

    //Related to Trainer
    val profilePicture: String?,
    val plansDocument: String,
    val fullName: String,
    val contactEmail: String,
    val contactPhone: String,
    val specialties: String,
    val age: String,
    val state: String,
    val city: String,
    val workLocation: String?,
    val friendshipCode: String,
    val profilePictureId: String?,
    val plansDocumentId: String?,
)

