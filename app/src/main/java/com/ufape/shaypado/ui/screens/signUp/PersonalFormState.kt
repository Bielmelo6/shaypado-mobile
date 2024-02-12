package com.ufape.shaypado.ui.screens.signUp

import androidx.annotation.StringRes

data class PersonalFormState(
    val profilePicture: String = "",
    @StringRes val profilePictureError: Int? = null,
    val name: String = "",
    @StringRes val nameError: Int? = null,
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val phone: String = "",
    @StringRes val phoneError: Int? = null,
    val specialties: String = "",
    @StringRes val specialtiesError: Int? = null,
    val age: String = "",
    @StringRes val ageError: Int? = null,
    val state: String = "",
    @StringRes val stateError: Int? = null,
    val city: String = "",
    @StringRes val cityError: Int? = null,
    val workLocation: String = "",
    @StringRes val workLocationError: Int? = null,
    val plansDocument: String = "",
    @StringRes val plansDocumentError: Int? = null,
)
