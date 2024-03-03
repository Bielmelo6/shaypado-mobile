package com.ufape.shaypado.ui.screens.signUp

import androidx.annotation.StringRes
import com.ufape.shaypado.data.model.TrainerRequest

data class PersonalFormState(
    val profilePicture: String? = null,
    @StringRes val profilePictureError: Int? = null,
    val profilePictureId: String? = null,
    val profilePictureUrl : String? = null,
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
    val plansDocument: String? = null,
    @StringRes val plansDocumentError: Int? = null,
    val plansDocumentId : String? = null,
    val plansDocumentUrl : String? = null
)

fun PersonalFormState.toRequest(
    userAccountFormState: UserAccountFormState
) : TrainerRequest {
    return TrainerRequest(
        name = userAccountFormState.name,
        email = userAccountFormState.email,
        password = userAccountFormState.password,
        profilePicture = profilePictureId,
        plansDocument = plansDocumentId!!,
        fullName = name,
        contactEmail = email,
        contactPhone = phone,
        specialties = specialties,
        age = age,
        state = state,
        city = city,
        workLocation = workLocation
    )
}

