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
import com.ufape.shaypado.ui.domain.use_case.validateUserType
import com.ufape.shaypado.ui.domain.use_case.validateWeight
import com.ufape.shaypado.ui.domain.use_case.validateWorkoutFrequency
import com.ufape.shaypado.ui.domain.use_case.validateWorkoutType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ufape.shaypado.util.Result


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
) : ViewModel() {
    var userAccountDataState by mutableStateOf(UserAccountFormState())
    var userPhysicalEvaluationDataState by mutableStateOf(UserPhysicalEvaluationFormState())

    private val _hasValidationErrors = MutableStateFlow(false)
    private val hasValidationErrors = _hasValidationErrors.asStateFlow()

    private val _currentTabIndex = MutableStateFlow(0)
    val currentTabIndex = _currentTabIndex.asStateFlow()

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

            is UserAccountFormEvent.OnSubmit -> {
                validateUserData()
            }
        }
    }

    fun onTabSelected(index: Int) {
        _currentTabIndex.value = index
    }

    fun onPhysicalEvaluationDataEvent(event: UserPhysicalEvaluationFormEvent) {
        when (event) {
            is UserPhysicalEvaluationFormEvent.OnWeightChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(weight = event.weight)
            }

            is UserPhysicalEvaluationFormEvent.OnHeightChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(height = event.height)
            }

            is UserPhysicalEvaluationFormEvent.OnObjectiveChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(objective = event.objective)
            }

            is UserPhysicalEvaluationFormEvent.OnWorkoutFrequencyChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(workoutFrequency = event.workoutFrequency)
            }

            is UserPhysicalEvaluationFormEvent.OnAnyDiseaseChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(anyDisease = event.anyDisease)
            }

            is UserPhysicalEvaluationFormEvent.OnCorporalDataChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(saveCorporalData = event.saveCorporalData)
            }

            is UserPhysicalEvaluationFormEvent.OnSubmit -> {
                validatePhysicalEvaluationData()
                register()
            }

        }
    }

    fun onUserTypeChanged(userType: String) {
        userAccountDataState = userAccountDataState.copy(userType = userType)
    }

    fun onWorkoutTypeChanged(workoutType: String) {
        userAccountDataState = userAccountDataState.copy(workoutType = workoutType)
    }

    fun onUserTypeNextButtonClicked() {
        val userTypeValidation = validateUserType(userAccountDataState.userType)
        userAccountDataState = userAccountDataState.copy(
            userTypeError = userTypeValidation.error
        )
        _hasValidationErrors.value = hasError(userTypeValidation)
        if (!hasValidationErrors.value) {
            _currentTabIndex.value = 2
        }
    }

    fun onWorkoutNextButtonClicked() {
        val workoutTypeValidation = validateWorkoutType(userAccountDataState.workoutType)
        userAccountDataState = userAccountDataState.copy(
            workoutTypeError = workoutTypeValidation.error
        )
        _hasValidationErrors.value = hasError(workoutTypeValidation)
        if (!hasValidationErrors.value) {
            _currentTabIndex.value = 3
        }
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

        _hasValidationErrors.value = hasError(
            nameValidation,
            emailValidation,
            emailConfirmationValidation,
            passwordValidation,
            passwordConfirmationValidation
        )

        if (!hasValidationErrors.value) {
            _currentTabIndex.value = 1
        }
    }

    private fun validatePhysicalEvaluationData() {
        val weightValidation = validateWeight(userPhysicalEvaluationDataState.weight)
        val heightValidation = validateHeight(userPhysicalEvaluationDataState.height)
        val workoutFrequencyValidation =
            validateWorkoutFrequency(userPhysicalEvaluationDataState.workoutFrequency)

        userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(
            weightError = weightValidation.error,
            heightError = heightValidation.error,
            workoutFrequencyError = workoutFrequencyValidation.error,
        )

        _hasValidationErrors.value = hasError(
            weightValidation,
            heightValidation,
            workoutFrequencyValidation,
        )
    }

    private fun register() {
        if (hasValidationErrors.value) return
        viewModelScope.launch {
            val userData = UserData(
                name = userAccountDataState.name,
                email = userAccountDataState.email,
                password = userAccountDataState.password,
                userType = userAccountDataState.userType,
                weight = userPhysicalEvaluationDataState.weight,
                height = userPhysicalEvaluationDataState.height,
                objective = userPhysicalEvaluationDataState.objective,
                anyDisease = userPhysicalEvaluationDataState.anyDisease.toString()
            )
            val result = authRepository.register(userData)
            registrationEventChannel.send(result)
        }
    }
}