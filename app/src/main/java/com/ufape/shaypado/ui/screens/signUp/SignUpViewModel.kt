package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.TrainerRequest
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.ui.domain.use_case.hasError
import com.ufape.shaypado.ui.domain.use_case.validateAge
import com.ufape.shaypado.ui.domain.use_case.validateCity
import com.ufape.shaypado.ui.domain.use_case.validateEmail
import com.ufape.shaypado.ui.domain.use_case.validateEmailConfirmation
import com.ufape.shaypado.ui.domain.use_case.validateHeight
import com.ufape.shaypado.ui.domain.use_case.validatePasswordConfirmation
import com.ufape.shaypado.ui.domain.use_case.validateName
import com.ufape.shaypado.ui.domain.use_case.validatePassword
import com.ufape.shaypado.ui.domain.use_case.validatePhone
import com.ufape.shaypado.ui.domain.use_case.validatePlansDocument
import com.ufape.shaypado.ui.domain.use_case.validateSpecialties
import com.ufape.shaypado.ui.domain.use_case.validateState
import com.ufape.shaypado.ui.domain.use_case.validateTermsAndConditions
import com.ufape.shaypado.ui.domain.use_case.validateWeight
import com.ufape.shaypado.util.ISafeNetworkHandler
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
    private val handler: ISafeNetworkHandler
) : ViewModel() {
    var userAccountDataState by mutableStateOf(UserAccountFormState())
    var userPhysicalEvaluationDataState by mutableStateOf(UserPhysicalEvaluationFormState())
    var personalFormDataState by mutableStateOf(PersonalFormState())

    private val _hasValidationErrors = MutableStateFlow<Result<Unit>>(Result.Loading)
    val validationStatus = _hasValidationErrors.asStateFlow()

    private val _trainerRegistrationEventChannel = Channel<Result<Unit>>()
    val trainerRegisterEvent = _trainerRegistrationEventChannel.receiveAsFlow()

    private val userRegistrationEventChannel = Channel<Result<Unit>>()
    val userRegisterEvent = userRegistrationEventChannel.receiveAsFlow()

    private fun registerUser() {
        if (validationStatus.value !is Result.Success) return
        viewModelScope.launch {
            val userRequest = UserRequest(
                name = userAccountDataState.name,
                email = userAccountDataState.email,
                password = userAccountDataState.password,
                userType = userAccountDataState.userType,
                weight = userAccountDataState.weight,
                height = userAccountDataState.height,
//                objective = userAccountDataState.objective,
                anyDisease = userAccountDataState.anyDisease
            )
            val result = handler.makeSafeApiCall { authRepository.registerUser(userRequest) }
            userRegistrationEventChannel.send(result)
            resetValidationStatus()
        }
    }

    fun registerUserWithPhysicalEvaluation() {
        //TODO Implement this
    }

    private fun registerPersonal() {
        if (_hasValidationErrors.value !is Result.Success) return
        viewModelScope.launch {
            val data = TrainerRequest(
                name = userAccountDataState.name,
                email = userAccountDataState.email,
                password = userAccountDataState.password,
                userType = userAccountDataState.userType,

                fullName = personalFormDataState.name,
                contactPhone = personalFormDataState.phone,
                contactEmail = personalFormDataState.email,
                specialties = personalFormDataState.specialties,
                age = personalFormDataState.age,
                state = personalFormDataState.state,
                city = personalFormDataState.city,
                workLocation = personalFormDataState.workLocation,
                plansDocument = personalFormDataState.plansDocument!!,
                profilePicture = personalFormDataState.profilePicture
            )
            val result = handler.makeSafeApiCall { authRepository.registerTrainer(data) }
            _trainerRegistrationEventChannel.send(result)
            resetValidationStatus()
        }
    }

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
                _hasValidationErrors.value = Result.Loading
                validateUserData()
            }

            is UserAccountFormEvent.ValidateProfileData -> {
                _hasValidationErrors.value = Result.Loading
                validateProfileData()
            }

            is UserAccountFormEvent.OnUserTypeChanged -> {
                userAccountDataState = userAccountDataState.copy(userType = event.userType)
            }

            is UserAccountFormEvent.OnSubmit -> {
                _hasValidationErrors.value =
                    if (!validateUserData() && !validateProfileData()) Result.Success(Unit) else Result.Error(
                        Exception("Validation error")
                    )
                if (_hasValidationErrors.value is Result.Success) {
                    registerUser()
                }
            }
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

            else -> {
                //TODO Implement this
            }
        }
    }

    fun onPersonalDataEvent(event: PersonalFormEvent) {
        when (event) {
            is PersonalFormEvent.OnNameChanged -> {
                personalFormDataState = personalFormDataState.copy(name = event.name)
            }

            is PersonalFormEvent.OnEmailChanged -> {
                personalFormDataState = personalFormDataState.copy(email = event.email)
            }

            is PersonalFormEvent.OnProfilePictureChanged -> {
                personalFormDataState =
                    personalFormDataState.copy(profilePicture = event.profilePicture)
            }

            is PersonalFormEvent.OnPhoneChanged -> {
                personalFormDataState = personalFormDataState.copy(phone = event.phone)
            }

            is PersonalFormEvent.OnSpecialtiesChanged -> {
                personalFormDataState = personalFormDataState.copy(specialties = event.specialties)
            }

            is PersonalFormEvent.OnAgeChanged -> {
                personalFormDataState = personalFormDataState.copy(age = event.age)
            }

            is PersonalFormEvent.OnStateChanged -> {
                personalFormDataState = personalFormDataState.copy(state = event.state)
            }

            is PersonalFormEvent.OnCityChanged -> {
                personalFormDataState = personalFormDataState.copy(city = event.city)
            }

            is PersonalFormEvent.OnWorkLocationChanged -> {
                personalFormDataState =
                    personalFormDataState.copy(workLocation = event.workLocation)
            }

            is PersonalFormEvent.OnPlansDocumentChanged -> {
                personalFormDataState =
                    personalFormDataState.copy(plansDocument = event.plansDocument)
            }

            is PersonalFormEvent.OnSubmit -> {
                validateTrainerData()
                registerPersonal()
            }
        }
    }

    private fun validateUserData(): Boolean {
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
        return if (hasAnyError) {
            _hasValidationErrors.value = Result.Error(Exception())
            true
        } else {
            _hasValidationErrors.value = Result.Success(Unit)
            false
        }
    }

    private fun validateProfileData(): Boolean {
        val weightValidation = validateWeight(userAccountDataState.weight)
        val heightValidation = validateHeight(userAccountDataState.height)
        val termsAcceptedValidation = validateTermsAndConditions(userAccountDataState.termsAccepted)

        userAccountDataState = userAccountDataState.copy(
            weightError = weightValidation.error,
            heightError = heightValidation.error,
            termsAcceptedError = termsAcceptedValidation.error
        )

        val hasAnyError = hasError(
            weightValidation,
            heightValidation,
            termsAcceptedValidation
        )
        return if (hasAnyError) {
            _hasValidationErrors.value = Result.Error(Exception())
            true
        } else {
            _hasValidationErrors.value = Result.Success(Unit)
            false
        }
    }

    fun validateTrainerData(): Boolean {
        val nameValidation = validateName(personalFormDataState.name)
        val emailValidation = validateEmail(personalFormDataState.email)
        val phoneValidation = validatePhone(personalFormDataState.phone)
        val specialtiesValidation = validateSpecialties(personalFormDataState.specialties)
        val ageValidation = validateAge(personalFormDataState.age)
        val stateValidation = validateState(personalFormDataState.state)
        val cityValidation = validateCity(personalFormDataState.city)
        val plansDocumentValidation = validatePlansDocument(personalFormDataState.plansDocument)

        personalFormDataState = personalFormDataState.copy(
            nameError = nameValidation.error,
            emailError = emailValidation.error,
            phoneError = phoneValidation.error,
            specialtiesError = specialtiesValidation.error,
            ageError = ageValidation.error,
            stateError = stateValidation.error,
            cityError = cityValidation.error,
            plansDocumentError = plansDocumentValidation.error
        )

        val hasAnyError = hasError(
            nameValidation,
            emailValidation,
            phoneValidation,
            specialtiesValidation,
            ageValidation,
            stateValidation,
            cityValidation,
            plansDocumentValidation
        )
        return if (hasAnyError) {
            _hasValidationErrors.value = Result.Error(Exception())
            true
        } else {
            _hasValidationErrors.value = Result.Success(Unit)
            false
        }
    }

    fun validaTePhysicalEvaluationData() {
        //TODO Implement this
    }

    fun resetValidationStatus() {
        _hasValidationErrors.value = Result.Loading
    }

}