package com.ufape.shaypado.ui.screens.signUp

sealed class PersonalFormEvent {
    data class OnProfilePictureChanged(val profilePicture: String) : PersonalFormEvent()

    data class OnNameChanged(val name: String) : PersonalFormEvent()

    data class OnEmailChanged(val email: String) : PersonalFormEvent()

    data class OnPhoneChanged(val phone: String) : PersonalFormEvent()

    data class OnSpecialtiesChanged(val specialties: String) : PersonalFormEvent()

    data class OnAgeChanged(val age: String) : PersonalFormEvent()

    data class OnStateChanged(val state: String) : PersonalFormEvent()

    data class OnCityChanged(val city: String) : PersonalFormEvent()

    data class OnWorkLocationChanged(val workLocation: String) : PersonalFormEvent()

    data class OnPlansDocumentChanged(val plansDocument: String) : PersonalFormEvent()

    data object OnSubmit : PersonalFormEvent()
}