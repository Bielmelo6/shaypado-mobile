package com.ufape.shaypado.ui.screens.signUp

sealed class UserAccountFormEvent {
    data class OnNameChanged(val name: String) : UserAccountFormEvent()
    data class OnEmailChanged(val email: String) : UserAccountFormEvent()
    data class OnEmailConfirmationChanged(val emailConfirmation: String) : UserAccountFormEvent()
    data class OnPasswordChanged(val password: String) : UserAccountFormEvent()
    data class OnPasswordConfirmationChanged(val passwordConfirmation: String) : UserAccountFormEvent()
    data class OnWeightChanged(val weight: String) : UserAccountFormEvent()
    data class OnHeightChanged(val height: String) : UserAccountFormEvent()
    data class OnObjectiveChanged(val objective: String) : UserAccountFormEvent()
    data class OnAnyDiseaseChanged(val anyDisease: Boolean) : UserAccountFormEvent()
    data class OnCorporalDataChanged(val saveCorporalData: Boolean) : UserAccountFormEvent()
    data class OnTermsAcceptedChanged(val termsAccepted: Boolean) : UserAccountFormEvent()

    data object ValidateUserData : UserAccountFormEvent()

    data object ValidateProfileData : UserAccountFormEvent()

    data object OnSubmit : UserAccountFormEvent()
}
