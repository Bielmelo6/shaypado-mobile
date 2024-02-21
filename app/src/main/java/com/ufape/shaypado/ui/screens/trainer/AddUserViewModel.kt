package com.ufape.shaypado.ui.screens.trainer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.ui.screens.signUp.UserAccountFormEvent
import com.ufape.shaypado.ui.screens.signUp.UserAccountFormState
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormEvent
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormState
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
    private val handler: ISafeNetworkHandler
) : ViewModel() {
    var userAccountDataState by mutableStateOf(UserAccountFormState())
    var userPhysicalEvaluationDataState by mutableStateOf(UserPhysicalEvaluationFormState())

    fun onUserDataEvent(event: UserAccountFormEvent) {
        when (event) {
            is UserAccountFormEvent.OnNameChanged -> {
                userAccountDataState = userAccountDataState.copy(name = event.name)
            }

            is UserAccountFormEvent.OnEmailChanged -> {
                userAccountDataState = userAccountDataState.copy(email = event.email)
            }

            is UserAccountFormEvent.OnEmailConfirmationChanged -> {
                userAccountDataState =
                    userAccountDataState.copy(emailConfirmation = event.emailConfirmation)
            }

            is UserAccountFormEvent.OnPasswordChanged -> {
                userAccountDataState = userAccountDataState.copy(password = event.password)
            }

            is UserAccountFormEvent.OnPasswordConfirmationChanged -> {
                userAccountDataState =
                    userAccountDataState.copy(passwordConfirmation = event.passwordConfirmation)
            }

            is UserAccountFormEvent.OnCorporalDataChanged -> {
                userAccountDataState =
                    userAccountDataState.copy(saveCorporalData = event.saveCorporalData)
            }

            is UserAccountFormEvent.OnTermsAcceptedChanged -> {
                userAccountDataState =
                    userAccountDataState.copy(termsAccepted = event.termsAccepted)
            }

            is UserAccountFormEvent.OnUserTypeChanged -> {
                userAccountDataState = userAccountDataState.copy(userType = event.userType)
            }

            else -> {}
        }
    }

    fun onPhysicalEvaluationDataEvent(event: UserPhysicalEvaluationFormEvent) {
        when (event) {
            is UserPhysicalEvaluationFormEvent.OnFatPercentageChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(fatPercentage = event.fatPercentage)
            }

            is UserPhysicalEvaluationFormEvent.OnArmCircumferenceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(armCircumference = event.armCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnWaistCircumferenceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(waistCircumference = event.waistCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnAbdomenCircumferenceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(abdomenCircumference = event.abdomenCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnHipCircumferenceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(hipCircumference = event.hipCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnThighCircumferenceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(thighCircumference = event.thighCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(legCircumference = event.legCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnHeightChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(height = event.height)
            }

            is UserPhysicalEvaluationFormEvent.OnWeightChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(weight = event.weight)
            }

            is UserPhysicalEvaluationFormEvent.OnAgeChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(age = event.age)
            }

            is UserPhysicalEvaluationFormEvent.OnShoulderCircumferenceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(shoulderCircumference = event.shoulderCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnTricepsFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(tricepsFold = event.tricepsFold)
            }

            is UserPhysicalEvaluationFormEvent.OnBicepsFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(bicepsFold = event.bicepsFold)
            }

            is UserPhysicalEvaluationFormEvent.OnChestFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(chestFold = event.chestFold)
            }

            is UserPhysicalEvaluationFormEvent.OnAxialFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(axialFold = event.axialFold)
            }

            is UserPhysicalEvaluationFormEvent.OnSuprailiacFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(suprailiacFold = event.suprailiacFold)
            }

            is UserPhysicalEvaluationFormEvent.OnAbdominalFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(abdominalFold = event.abdominalFold)
            }

            is UserPhysicalEvaluationFormEvent.OnThighFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(thighFold = event.thighFold)
            }

            is UserPhysicalEvaluationFormEvent.OnLegFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(legFold = event.legFold)
            }

            is UserPhysicalEvaluationFormEvent.OnExerciseExperienceChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(exerciseExperience = event.exerciseExperience)
            }

            is UserPhysicalEvaluationFormEvent.OnHealthIssueChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(healthIssue = event.healthIssue)
            }

            is UserPhysicalEvaluationFormEvent.OnSmokerChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(isSmoker = event.isSmoker)
            }

            is UserPhysicalEvaluationFormEvent.OnSpineProblemChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(spineProblem = event.hasSpineProblem)
            }

            is UserPhysicalEvaluationFormEvent.OnScapularFoldChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(scapularFold = event.scapularFold)
            }

            is UserPhysicalEvaluationFormEvent.OnObjectiveChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(objective = event.objective)
            }

            is UserPhysicalEvaluationFormEvent.OnSubmit -> {
                registerUser()
            }
        }
    }

    private fun registerUser() {

    }
}