package com.ufape.shaypado.ui.model

import com.ufape.shaypado.ui.screens.trainer.importFriends.FriendsState

data class TrainerProfileData (
    val name: String,
    val email: String,

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
    val friendshipCode : String,
)