package com.ufape.shaypado.ui.screens.trainer.workoutSheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormEvent
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormState
import com.ufape.shaypado.ui.screens.trainer.createUser.UserFormEvent
import com.ufape.shaypado.ui.screens.trainer.createUser.UserFormState
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutSheetViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val trainerRepository: ITrainerRepository
) : ViewModel() {

    var studentsData by mutableStateOf(UserPhysicalEvaluationFormState())

    var userName by mutableStateOf("")


    private fun updatePhysicalEvaluation() {

    }


    fun onUserDataEvent(event: UserPhysicalEvaluationFormEvent) {
        when (event) {
            is UserPhysicalEvaluationFormEvent.OnAbdomenCircumferenceChanged ->  {
                studentsData = studentsData.copy(abdomenCircumference = event.abdomenCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnAbdominalFoldChanged ->  {
                studentsData = studentsData.copy(abdominalFold = event.abdominalFold)
            }
            is UserPhysicalEvaluationFormEvent.OnAgeChanged ->  {
                studentsData = studentsData.copy(age = event.age)
            }
            is UserPhysicalEvaluationFormEvent.OnArmCircumferenceChanged -> {
                studentsData = studentsData.copy(armCircumference = event.armCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnAxialFoldChanged ->  {
                studentsData = studentsData.copy(axialFold = event.axialFold)
            }
            is UserPhysicalEvaluationFormEvent.OnBicepsFoldChanged ->  {
                studentsData = studentsData.copy(bicepsFold = event.bicepsFold)
            }
            is UserPhysicalEvaluationFormEvent.OnChestFoldChanged ->  {
                studentsData = studentsData.copy(chestFold = event.chestFold)
            }
            is UserPhysicalEvaluationFormEvent.OnExerciseExperienceChanged ->  {
                studentsData = studentsData.copy(exerciseExperience = event.exerciseExperience)
            }
            is UserPhysicalEvaluationFormEvent.OnFatPercentageChanged ->  {
                studentsData = studentsData.copy(fatPercentage = event.fatPercentage)
            }
            is UserPhysicalEvaluationFormEvent.OnGenderChanged ->  {
                studentsData = studentsData.copy(gender = event.gender)
            }
            is UserPhysicalEvaluationFormEvent.OnHealthIssueChanged ->  {
                studentsData = studentsData.copy(healthIssue = event.healthIssue)
            }
            is UserPhysicalEvaluationFormEvent.OnHeightChanged ->  {
                studentsData = studentsData.copy(height = event.height)
            }
            is UserPhysicalEvaluationFormEvent.OnHipCircumferenceChanged ->  {
                studentsData = studentsData.copy(hipCircumference = event.hipCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged ->  {
                studentsData = studentsData.copy(legCircumference = event.legCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnLegFoldChanged ->  {
                studentsData = studentsData.copy(legFold = event.legFold)
            }
            is UserPhysicalEvaluationFormEvent.OnObjectiveChanged ->  {
                studentsData = studentsData.copy(objective = event.objective)
            }
            is UserPhysicalEvaluationFormEvent.OnScapularFoldChanged -> {
                studentsData = studentsData.copy(scapularFold = event.scapularFold)
            }
            is UserPhysicalEvaluationFormEvent.OnShoulderCircumferenceChanged ->  {
                studentsData = studentsData.copy(shoulderCircumference = event.shoulderCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnSmokerChanged ->  {
                studentsData = studentsData.copy(isSmoker = event.isSmoker)
            }
            is UserPhysicalEvaluationFormEvent.OnSpineProblemChanged ->  {
                studentsData = studentsData.copy(spineProblem = event.hasSpineProblem)
            }
            is UserPhysicalEvaluationFormEvent.OnSuprailiacFoldChanged ->  {
                studentsData = studentsData.copy(suprailiacFold = event.suprailiacFold)
            }
            is UserPhysicalEvaluationFormEvent.OnThighCircumferenceChanged ->  {
                studentsData = studentsData.copy(thighCircumference = event.thighCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnThighFoldChanged ->  {
                studentsData = studentsData.copy(thighFold = event.thighFold)
            }
            is UserPhysicalEvaluationFormEvent.OnTricepsFoldChanged ->  {
                studentsData = studentsData.copy(tricepsFold = event.tricepsFold)
            }
            is UserPhysicalEvaluationFormEvent.OnWaistCircumferenceChanged ->  {
                studentsData = studentsData.copy(waistCircumference = event.waistCircumference)
            }
            is UserPhysicalEvaluationFormEvent.OnWeightChanged ->  {
                studentsData = studentsData.copy(weight = event.weight)
            }

            is UserPhysicalEvaluationFormEvent.OnSubmit ->  {
                updatePhysicalEvaluation()
            }
        }
    }
}