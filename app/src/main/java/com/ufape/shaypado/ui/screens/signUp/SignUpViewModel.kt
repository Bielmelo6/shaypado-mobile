package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.LoginResponse
import com.ufape.shaypado.data.model.UserData
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.ui.domain.use_case.hasError
import com.ufape.shaypado.ui.domain.use_case.validateEmail
import com.ufape.shaypado.ui.domain.use_case.validateEmailConfirmation
import com.ufape.shaypado.ui.domain.use_case.validateHeight
import com.ufape.shaypado.ui.domain.use_case.validatePasswordConfirmation
import com.ufape.shaypado.ui.domain.use_case.validateName
import com.ufape.shaypado.ui.domain.use_case.validatePassword
import com.ufape.shaypado.ui.domain.use_case.validateWeight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ufape.shaypado.util.Result
import java.lang.Exception


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
) : ViewModel() {
    var userAccountDataState by mutableStateOf(UserAccountFormState())
    var userPhysicalEvaluationDataState by mutableStateOf(UserPhysicalEvaluationFormState())

    private val _hasUserDataValidationErrors = MutableStateFlow<Result<Unit>>(Result.Loading)
    val validationStatus = _hasUserDataValidationErrors.asStateFlow()

    private val registrationEventChannel = Channel<Result<LoginResponse>>()
    val registerEvent = registrationEventChannel.receiveAsFlow()

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

            is UserAccountFormEvent.OnWeightChanged -> {
                userAccountDataState = userAccountDataState.copy(weight = event.weight)
            }

            is UserAccountFormEvent.OnHeightChanged -> {
                userAccountDataState = userAccountDataState.copy(height = event.height)
            }

            is UserAccountFormEvent.OnObjectiveChanged -> {
                userAccountDataState = userAccountDataState.copy(objective = event.objective)
            }

            is UserAccountFormEvent.OnAnyDiseaseChanged -> {
                userAccountDataState = userAccountDataState.copy(anyDisease = event.anyDisease)
            }

            is UserAccountFormEvent.OnCorporalDataChanged -> {
                userAccountDataState =
                    userAccountDataState.copy(saveCorporalData = event.saveCorporalData)
            }

            is UserAccountFormEvent.OnTermsAcceptedChanged -> {
                userAccountDataState =
                    userAccountDataState.copy(termsAccepted = event.termsAccepted)
            }

            is UserAccountFormEvent.ValidateUserData -> {
                _hasUserDataValidationErrors.value = Result.Loading
                validateUserData()
            }

            is UserAccountFormEvent.ValidateProfileData -> {
                _hasUserDataValidationErrors.value = Result.Loading
                validatePhysicalEvaluationData()
            }

            is UserAccountFormEvent.OnSubmit -> {
                validateUserData()
            }
        }
    }

    fun onPhysicalEvaluationDataEvent(event: UserPhysicalEvaluationFormEvent) {
        when (event) {
            is UserPhysicalEvaluationFormEvent.OnFatPercentageChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(fatPercentage = event.fatPercentage)
            }
            is UserPhysicalEvaluationFormEvent.OnArmCircumferenceChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(armCircumference = event.armCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnWaistCircumferenceChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(waistCircumference = event.waistCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnAbdomenCircumferenceChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(abdomenCircumference = event.abdomenCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnHipCircumferenceChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(hipCircumference = event.hipCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnThighCircumferenceChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(thighCircumference = event.thighCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(legCircumference = event.legCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnSubmit -> {
                validatePhysicalEvaluationData()
            }
        }
    }

    fun resetValidationStatus() {
        _hasUserDataValidationErrors.value = Result.Loading
    }

    private fun validateUserData() {
        val nameValidation = validateName(userAccountDataState.name)
        val emailValidation = validateEmail(userAccountDataState.email)
        val emailConfirmationValidation = validateEmailConfirmation(
            userAccountDataState.email,
            userAccountDataState.emailConfirmation
        )
        val passwordValidation = validatePassword(userAccountDataState.password)
        val passwordConfirmationValidation =
            validatePasswordConfirmation(
                userAccountDataState.password,
                userAccountDataState.passwordConfirmation
            )

        userAccountDataState = userAccountDataState.copy(
            nameError = nameValidation.error,
            emailError = emailValidation.error,
            passwordError = passwordValidation.error,
            passwordConfirmationError = passwordConfirmationValidation.error,
            emailConfirmationError = emailConfirmationValidation.error,
        )

        val hasAnyError = hasError(
            nameValidation,
            emailValidation,
            emailConfirmationValidation,
            passwordValidation,
            passwordConfirmationValidation
        )
        if (hasAnyError) {
            _hasUserDataValidationErrors.value = Result.Error(Exception())
        } else {
            _hasUserDataValidationErrors.value = Result.Success(Unit)

        }
    }

    private fun validatePhysicalEvaluationData() {
        val weightValidation = validateWeight(userAccountDataState.weight)
        val heightValidation = validateHeight(userAccountDataState.height)

        userAccountDataState = userAccountDataState.copy(
            weightError = weightValidation.error,
            heightError = heightValidation.error,
        )

        val hasAnyError = hasError(
            weightValidation,
            heightValidation,
        )
        if (hasAnyError) {
            _hasUserDataValidationErrors.value = Result.Error(Exception())
        } else {
            _hasUserDataValidationErrors.value = Result.Success(Unit)
        }
    }

    private fun register() {
        if (validationStatus.value is Result.Success) return
        viewModelScope.launch {
            val userData = UserData(
                name = userAccountDataState.name,
                email = userAccountDataState.email,
                password = userAccountDataState.password,
                userType = userAccountDataState.userType,
                weight = userAccountDataState.weight,
                height = userAccountDataState.height,
                objective = userAccountDataState.objective,
                anyDisease = userAccountDataState.anyDisease.toString()
            )
            val result = authRepository.register(userData)
            registrationEventChannel.send(result)
        }
    }
}