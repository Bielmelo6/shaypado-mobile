package com.ufape.shaypado.ui.screens.signUp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.BodyFatRequest
import com.ufape.shaypado.data.model.TrainerRequest
import com.ufape.shaypado.data.model.UserRequest
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.data.repositories.interfaces.IIaRepository
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
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
    private val handler: ISafeNetworkHandler,
    private val trainerRepository: ITrainerRepository,
    private val apiRepository: IIaRepository,
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

    var imagePath by mutableStateOf<String?>(null)

    private fun registerUser() {
        if (validationStatus.value !is Result.Success) return
        viewModelScope.launch {
            val userRequest = userAccountDataState.toRequest(userPhysicalEvaluationDataState)
            val result = handler.makeSafeApiCall { authRepository.registerUser(userRequest) }
            userRegistrationEventChannel.send(result)
            resetValidationStatus()
        }
    }

    private fun registerPersonal() {
        if (_hasValidationErrors.value !is Result.Success) return
        viewModelScope.launch {
            val plansDocumentId =
                handler.makeSafeApiCall { authRepository.uploadPlansDocument(personalFormDataState.plansDocument!!) }
            if (personalFormDataState.profilePicture != null) {
                val profilePictureId = handler.makeSafeApiCall {
                    authRepository.uploadProfilePicture(personalFormDataState.profilePicture!!)
                }
                if (profilePictureId is Result.Success) {
                    personalFormDataState =
                        personalFormDataState.copy(profilePictureId = profilePictureId.data.id)
                }
            }
            if (plansDocumentId is Result.Success) {
                personalFormDataState =
                    personalFormDataState.copy(plansDocumentId = plansDocumentId.data.id)
                val data = personalFormDataState.toRequest(userAccountDataState)
                val result = handler.makeSafeApiCall { authRepository.registerTrainer(data) }
                _trainerRegistrationEventChannel.send(result)
                resetValidationStatus()
            }
        }
    }

    fun fetchBodyFat(imagePath: String) {
        Log.d("SignUpViewModel", "fetchBodyFat: $imagePath")
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                apiRepository.fetchBodyFat(
                    BodyFatRequest(
                        image = imagePath,
                        height = userPhysicalEvaluationDataState.height,
                        gender = userPhysicalEvaluationDataState.gender
                    )
                )
            }

            Log.d("SignUpViewModel", "fetchBodyFat: $result")
            if (result is Result.Success) {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(fatPercentage = result.data.category)
            }
        }
    }

    fun fetchUserProfileData() {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                trainerRepository.fetchTrainerProfile()
            }
            if (result is Result.Success) {
                val data = result.data
                userAccountDataState = userAccountDataState.copy(
                    name = data.name,
                    email = data.email,
                )
                personalFormDataState = personalFormDataState.copy(
                    profilePictureUrl = data.profilePicture,
                    name = data.fullName,
                    email = data.contactEmail,
                    phone = data.contactPhone,
                    specialties = data.specialties,
                    age = data.age,
                    state = data.state,
                    city = data.city,
                    workLocation = data.workLocation ?: "",
                    plansDocumentUrl = data.plansDocument,
                    plansDocumentId = data.plansDocumentId,
                    profilePictureId = data.profilePictureId
                )
            }
        }
    }

    private fun updateTrainer() {
        if (_hasValidationErrors.value !is Result.Success) return
        viewModelScope.launch {
            if (personalFormDataState.plansDocument != null) {
                val plansDocumentId =
                    handler.makeSafeApiCall {
                        authRepository.uploadPlansDocument(
                            personalFormDataState.plansDocument!!
                        )
                    }
                if (plansDocumentId is Result.Success) {
                    personalFormDataState =
                        personalFormDataState.copy(plansDocumentId = plansDocumentId.data.id)
                }
            }


            if (personalFormDataState.profilePicture != null) {
                val profilePictureId = handler.makeSafeApiCall {
                    authRepository.uploadProfilePicture(personalFormDataState.profilePicture!!)
                }
                if (profilePictureId is Result.Success) {
                    personalFormDataState =
                        personalFormDataState.copy(profilePictureId = profilePictureId.data.id)
                }
            }

            val data = personalFormDataState.toRequest(userAccountDataState)
            val result = handler.makeSafeApiCall { authRepository.updateTrainer(data) }
            _trainerRegistrationEventChannel.send(result)
            resetValidationStatus()
        }
    }

    private fun updateUserData() {
        if (_hasValidationErrors.value !is Result.Success) return
        viewModelScope.launch {
            val userRequest = userAccountDataState.toRequest(userPhysicalEvaluationDataState)
            val result = handler.makeSafeApiCall { authRepository.updateUser(userRequest) }
            userRegistrationEventChannel.send(result)
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
                validateProfileData(validateTerms = false)
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

            is UserPhysicalEvaluationFormEvent.OnHeightChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(height = event.height)
            }

            is UserPhysicalEvaluationFormEvent.OnWeightChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(weight = event.weight)
            }

            is UserPhysicalEvaluationFormEvent.OnHealthIssueChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(healthIssue = event.healthIssue)
            }

            is UserPhysicalEvaluationFormEvent.OnObjectiveChanged -> {
                userPhysicalEvaluationDataState =
                    userPhysicalEvaluationDataState.copy(objective = event.objective)
            }

            is UserPhysicalEvaluationFormEvent.OnGenderChanged -> {
                userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(
                    gender = event.gender
                )
            }

            else -> {}
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

            is PersonalFormEvent.OnUpdate -> {
                updateTrainer()
            }
        }
    }


    private fun validateUserData(): Boolean {
        val nameValidation = validateName(userAccountDataState.name)
        val emailValidation = validateEmail(userAccountDataState.email)
        val emailConfirmationValidation = validateEmailConfirmation(
            userAccountDataState.email, userAccountDataState.emailConfirmation
        )
        val passwordValidation = validatePassword(userAccountDataState.password)
        val passwordConfirmationValidation = validatePasswordConfirmation(
            userAccountDataState.password, userAccountDataState.passwordConfirmation
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

    private fun validateProfileData(validateTerms: Boolean = true): Boolean {
        val weightValidation = validateWeight(userPhysicalEvaluationDataState.weight)
        val heightValidation = validateHeight(userPhysicalEvaluationDataState.height)
        val termsAcceptedValidation = validateTermsAndConditions(userAccountDataState.termsAccepted)

        userPhysicalEvaluationDataState = userPhysicalEvaluationDataState.copy(
            weightError = weightValidation.error, heightError = heightValidation.error
        )

        val hasAnyError = if (validateTerms) {
            userAccountDataState = userAccountDataState.copy(
                termsAcceptedError = termsAcceptedValidation.error
            )

            hasError(
                weightValidation, heightValidation, termsAcceptedValidation
            )
        } else {
            hasError(
                weightValidation,
                heightValidation,
            )
        }

        return if (hasAnyError) {
            _hasValidationErrors.value = Result.Error(Exception())
            true
        } else {
            _hasValidationErrors.value = Result.Success(Unit)
            false
        }
    }

    private fun validateTrainerData(): Boolean {
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

    fun resetValidationStatus() {
        _hasValidationErrors.value = Result.Loading
    }

}