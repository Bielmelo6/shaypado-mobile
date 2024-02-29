package com.ufape.shaypado.ui.screens.trainer.createUser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.data.repositories.interfaces.IAuthRepository
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val authRepository: IAuthRepository,
    private val handler: ISafeNetworkHandler
) : ViewModel() {
    var numberOfStudents by mutableIntStateOf(0)

    var selectedStudent by mutableIntStateOf(0)

    var studentsData by mutableStateOf<List<UserFormState>>(listOf())

    fun onUserDataEvent(event : UserFormEvent){
        when (event) {
            is UserFormEvent.OnNameChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(name = event.name)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnEmailChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(email = event.email)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnEmailConfirmationChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(emailConfirmation = event.emailConfirmation)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnPasswordChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(password = event.password)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnPasswordConfirmationChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(passwordConfirmation = event.passwordConfirmation)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnCorporalDataChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(saveCorporalData = event.saveCorporalData)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnTermsAcceptedChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(termsAccepted = event.termsAccepted)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnUserTypeChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(userType = event.userType)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnFatPercentageChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(fatPercentage = event.fatPercentage)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnArmCircumferenceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(armCircumference = event.armCircumference)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnWaistCircumferenceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(waistCircumference = event.waistCircumference)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnAbdomenCircumferenceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(abdomenCircumference = event.abdomenCircumference)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnHipCircumferenceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(hipCircumference = event.hipCircumference)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnThighCircumferenceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(thighCircumference = event.thighCircumference)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnLegCircumferenceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(legCircumference = event.legCircumference)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnHeightChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(height = event.height)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnWeightChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(weight = event.weight)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnAgeChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(age = event.age)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnShoulderCircumferenceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(shoulderCircumference = event.shoulderCircumference)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnTricepsFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(tricepsFold = event.tricepsFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnBicepsFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(bicepsFold = event.bicepsFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnChestFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(chestFold = event.chestFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnAxialFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(axialFold = event.axialFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnSuprailiacFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(suprailiacFold = event.suprailiacFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnAbdominalFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(abdominalFold = event.abdominalFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnThighFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(thighFold = event.thighFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnLegFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(legFold = event.legFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnExerciseExperienceChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(exerciseExperience = event.exerciseExperience)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnHealthIssueChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(healthIssue = event.healthIssue)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnSmokerChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(isSmoker = event.isSmoker)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnSpineProblemChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(spineProblem = event.hasSpineProblem)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnScapularFoldChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(scapularFold = event.scapularFold)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnObjectiveChanged -> {
                val newData = studentsData.mapIndexed{ index, userFormState ->
                    if (index == selectedStudent) {
                        userFormState.copy(objective = event.objective)
                    } else {
                        userFormState
                    }
                }
                studentsData = newData
            }

            is UserFormEvent.OnSubmit -> {
                registerUser()
            }
        }
    }

    fun allocateStudents() {
        numberOfStudents++
        studentsData = List(numberOfStudents) { UserFormState() }
    }

    fun increaseStudents() {
        numberOfStudents++
    }

    fun decreaseStudents() {
        numberOfStudents--
    }

    fun increaseSelectedStudent() {
        selectedStudent++
    }

    fun decreaseSelectedStudent() {
        selectedStudent--
    }

    private fun registerUser() {

    }
}