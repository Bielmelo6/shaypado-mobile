package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.TrainerProfileData

data class TrainerRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,

    //Related to Trainer
    @SerializedName("profile_picture")
    val profilePicture: String?,
    @SerializedName("plans_document")
    val plansDocument: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("contact_email")
    val contactEmail: String,
    @SerializedName("contact_phone")
    val contactPhone: String,
    @SerializedName("specialties")
    val specialties: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("work_location")
    val workLocation: String?,
)

data class TrainerResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,

    //Related to Trainer
    @SerializedName("profile_picture")
    val profilePicture: String?,
    @SerializedName("plans_document")
    val plansDocument: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("contact_email")
    val contactEmail: String,
    @SerializedName("contact_phone")
    val contactPhone: String,
    @SerializedName("specialties")
    val specialties: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("work_location")
    val workLocation: String?,
    @SerializedName("friendship_code")
    val friendshipCode: String,
    @SerializedName("profile_picture_id")
    val profilePictureId: String?,
    @SerializedName("plans_document_id")
    val plansDocumentId: String?,
)

fun TrainerResponse.toUiModel(): TrainerProfileData {
    return TrainerProfileData(
        name = this.name,
        email = this.email,
        profilePicture = this.profilePicture,
        plansDocument = this.plansDocument,
        fullName = this.fullName,
        contactEmail = this.contactEmail,
        contactPhone = this.contactPhone,
        specialties = this.specialties,
        age = this.age,
        state = this.state,
        city = this.city,
        workLocation = this.workLocation,
        friendshipCode = this.friendshipCode,
        plansDocumentId = this.plansDocumentId,
        profilePictureId = this.profilePictureId
    )
}

