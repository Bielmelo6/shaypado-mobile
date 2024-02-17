package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName


data class TrainerRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("userType")
    val userType: String,

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