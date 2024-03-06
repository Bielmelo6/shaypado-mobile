package com.ufape.shaypado.ui.screens.trainer.workoutSheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.ui.screens.trainer.createUser.UserFormEvent
import com.ufape.shaypado.ui.screens.trainer.createUser.UserFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutSheetViewModel @Inject constructor(

) : ViewModel() {

    var studentsData by mutableStateOf(UserFormState())


    fun onUserDataEvent(event: UserFormEvent) {
        when (event) {
            is UserFormEvent.OnNameChanged -> {
                studentsData = studentsData.copy(name = event.name)
            }

            is UserFormEvent.OnEmailChanged -> {
                studentsData = studentsData.copy(email = event.email)
            }

            is UserFormEvent.OnAbdomenCircumferenceChanged -> {
                studentsData = studentsData.copy(abdomenCircumference = event.abdomenCircumference)
            }

            is UserFormEvent.OnAbdominalFoldChanged -> {
                studentsData = studentsData.copy(abdominalFold = event.abdominalFold)
            }

            is UserFormEvent.OnAgeChanged -> {
                studentsData = studentsData.copy(age = event.age)
            }

            is UserFormEvent.OnArmCircumferenceChanged -> {
                studentsData = studentsData.copy(armCircumference = event.armCircumference)
            }

            is UserFormEvent.OnAxialFoldChanged -> {
                studentsData = studentsData.copy(axialFold = event.axialFold)
            }

            is UserFormEvent.OnBicepsFoldChanged -> {
                studentsData = studentsData.copy(bicepsFold = event.bicepsFold)
            }

            is UserFormEvent.OnChestFoldChanged -> {
                studentsData = studentsData.copy(chestFold = event.chestFold)
            }

            is UserFormEvent.OnExerciseExperienceChanged -> {
                studentsData = studentsData.copy(exerciseExperience = event.exerciseExperience)
            }

            is UserFormEvent.OnFatPercentageChanged -> {
                studentsData = studentsData.copy(fatPercentage = event.fatPercentage)
            }

            is UserFormEvent.OnHealthIssueChanged -> {
                studentsData = studentsData.copy(healthIssue = event.healthIssue)
            }

            is UserFormEvent.OnHeightChanged -> {
                studentsData = studentsData.copy(height = event.height)
            }

            is UserFormEvent.OnHipCircumferenceChanged -> {
                studentsData = studentsData.copy(hipCircumference = event.hipCircumference)
            }

            is UserFormEvent.OnLegCircumferenceChanged -> {
                studentsData = studentsData.copy(legCircumference = event.legCircumference)
            }

            is UserFormEvent.OnLegFoldChanged -> {
                studentsData = studentsData.copy(legFold = event.legFold)
            }

            is UserFormEvent.OnObjectiveChanged -> {
                studentsData = studentsData.copy(objective = event.objective)
            }

            is UserFormEvent.OnScapularFoldChanged -> {
                studentsData = studentsData.copy(scapularFold = event.scapularFold)
            }

            is UserFormEvent.OnShoulderCircumferenceChanged -> {
                studentsData =
                    studentsData.copy(shoulderCircumference = event.shoulderCircumference)
            }

            is UserFormEvent.OnSmokerChanged -> {
                studentsData = studentsData.copy(isSmoker = event.isSmoker)
            }

            is UserFormEvent.OnSpineProblemChanged -> {
                studentsData = studentsData.copy(spineProblem = event.hasSpineProblem)
            }

            is UserFormEvent.OnSuprailiacFoldChanged -> {
                studentsData = studentsData.copy(suprailiacFold = event.suprailiacFold)
            }

            is UserFormEvent.OnThighCircumferenceChanged -> {
                studentsData = studentsData.copy(thighCircumference = event.thighCircumference)
            }

            is UserFormEvent.OnThighFoldChanged -> {
                studentsData = studentsData.copy(thighFold = event.thighFold)
            }

            is UserFormEvent.OnTricepsFoldChanged -> {
                studentsData = studentsData.copy(tricepsFold = event.tricepsFold)
            }

            is UserFormEvent.OnUserTypeChanged -> {
                studentsData = studentsData.copy(userType = event.userType)
            }

            is UserFormEvent.OnWaistCircumferenceChanged -> {
                studentsData = studentsData.copy(waistCircumference = event.waistCircumference)
            }

            is UserFormEvent.OnWeightChanged -> {
                studentsData = studentsData.copy(weight = event.weight)
            }

            UserFormEvent.OnSubmit -> {}

            else -> {}
        }
    }
}