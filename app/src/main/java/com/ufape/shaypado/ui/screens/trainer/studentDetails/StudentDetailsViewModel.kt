package com.ufape.shaypado.ui.screens.trainer.studentDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.BodyFatRequest
import com.ufape.shaypado.data.repositories.interfaces.IIaRepository
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.PhysicalFormState
import com.ufape.shaypado.ui.model.UserWithWorkoutState
import com.ufape.shaypado.ui.model.getPhysicalData
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormEvent
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentDetailsViewModel @Inject constructor(
    private val trainerRepository: ITrainerRepository,
    private val handler: ISafeNetworkHandler,
    private val apiRepository: IIaRepository
) : ViewModel() {

    private val _studentData = Channel<Result<UserWithWorkoutState>>()
    val studentData = _studentData.receiveAsFlow()

    var physicalFormState by mutableStateOf(PhysicalFormState())

    fun fetchStudent(id: String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                trainerRepository.fetchStudent(id)
            }


            if (result is Result.Success) {
                _studentData.send(Result.Success(result.data))
                physicalFormState = result.data.getPhysicalData()
            } else if (result is Result.Error) {
                _studentData.send(Result.Error(result.exception))
            }
        }
    }

    fun fetchBodyFat(imagePath: String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                apiRepository.fetchBodyFat(
                    BodyFatRequest(
                        image = imagePath,
                        height = physicalFormState.height,
                        gender = physicalFormState.gender
                    )
                )
            }

            if (result is Result.Success) {
                physicalFormState = physicalFormState.copy(fatPercentage = result.data.category)
            }
        }
    }

    fun onUserDataEvent(event: UserPhysicalEvaluationFormEvent) {
        when (event) {
            is UserPhysicalEvaluationFormEvent.OnAbdomenCircumferenceChanged -> {
                physicalFormState =
                    physicalFormState.copy(abdomenCircumference = event.abdomenCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnAbdominalFoldChanged -> {
                physicalFormState = physicalFormState.copy(abdominalFold = event.abdominalFold)
            }

            is UserPhysicalEvaluationFormEvent.OnAgeChanged -> {
                physicalFormState = physicalFormState.copy(age = event.age)
            }

            is UserPhysicalEvaluationFormEvent.OnArmCircumferenceChanged -> {
                physicalFormState =
                    physicalFormState.copy(armCircumference = event.armCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnAxialFoldChanged -> {
                physicalFormState = physicalFormState.copy(axialFold = event.axialFold)
            }

            is UserPhysicalEvaluationFormEvent.OnBicepsFoldChanged -> {
                physicalFormState = physicalFormState.copy(bicepsFold = event.bicepsFold)
            }

            is UserPhysicalEvaluationFormEvent.OnChestFoldChanged -> {
                physicalFormState = physicalFormState.copy(chestFold = event.chestFold)
            }

            is UserPhysicalEvaluationFormEvent.OnExerciseExperienceChanged -> {
                physicalFormState =
                    physicalFormState.copy(exerciseExperience = event.exerciseExperience)
            }

            is UserPhysicalEvaluationFormEvent.OnFatPercentageChanged -> {
                physicalFormState = physicalFormState.copy(fatPercentage = event.fatPercentage)
            }

            is UserPhysicalEvaluationFormEvent.OnGenderChanged -> {
                physicalFormState = physicalFormState.copy(gender = event.gender)
            }

            is UserPhysicalEvaluationFormEvent.OnHealthIssueChanged -> {
                physicalFormState = physicalFormState.copy(healthIssue = event.healthIssue)
            }

            is UserPhysicalEvaluationFormEvent.OnHeightChanged -> {
                physicalFormState = physicalFormState.copy(height = event.height)
            }

            is UserPhysicalEvaluationFormEvent.OnHipCircumferenceChanged -> {
                physicalFormState =
                    physicalFormState.copy(hipCircumference = event.hipCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged -> {
                physicalFormState =
                    physicalFormState.copy(legCircumference = event.legCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnLegFoldChanged -> {
                physicalFormState = physicalFormState.copy(legFold = event.legFold)
            }

            is UserPhysicalEvaluationFormEvent.OnObjectiveChanged -> {
                physicalFormState = physicalFormState.copy(objective = event.objective)
            }

            is UserPhysicalEvaluationFormEvent.OnScapularFoldChanged -> {
                physicalFormState = physicalFormState.copy(scapularFold = event.scapularFold)
            }

            is UserPhysicalEvaluationFormEvent.OnShoulderCircumferenceChanged -> {
                physicalFormState =
                    physicalFormState.copy(shoulderCircumference = event.shoulderCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnSmokerChanged -> {
                physicalFormState = physicalFormState.copy(isSmoker = event.isSmoker)
            }

            is UserPhysicalEvaluationFormEvent.OnSpineProblemChanged -> {
                physicalFormState = physicalFormState.copy(spineProblem = event.hasSpineProblem)
            }

            is UserPhysicalEvaluationFormEvent.OnSuprailiacFoldChanged -> {
                physicalFormState = physicalFormState.copy(suprailiacFold = event.suprailiacFold)
            }

            is UserPhysicalEvaluationFormEvent.OnThighCircumferenceChanged -> {
                physicalFormState =
                    physicalFormState.copy(thighCircumference = event.thighCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnThighFoldChanged -> {
                physicalFormState = physicalFormState.copy(thighFold = event.thighFold)
            }

            is UserPhysicalEvaluationFormEvent.OnTricepsFoldChanged -> {
                physicalFormState = physicalFormState.copy(tricepsFold = event.tricepsFold)
            }

            is UserPhysicalEvaluationFormEvent.OnWaistCircumferenceChanged -> {
                physicalFormState =
                    physicalFormState.copy(waistCircumference = event.waistCircumference)
            }

            is UserPhysicalEvaluationFormEvent.OnWeightChanged -> {
                physicalFormState = physicalFormState.copy(weight = event.weight)
            }

            is UserPhysicalEvaluationFormEvent.OnSubmit -> {
                updatePhysicalData()
            }
        }
    }

    fun updatePhysicalData() {
//        viewModelScope.launch {
//            val result = handler.makeSafeApiCall {
//
//            }
//
//            if (result is Result.Success) {
//                _studentData.send(Result.Success(result.data))
//                physicalFormState = result.data.getPhysicalData()
//            } else if (result is Result.Error) {
//                _studentData.send(Result.Error(result.exception))
//            }
//        }
    }

}