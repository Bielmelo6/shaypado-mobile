package com.ufape.shaypado.ui.screens.addTraining

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AddTrainingViewModel @Inject constructor() : ViewModel(){
    private val addTrainingEventChannel = Channel<Result<Boolean>>()
    val addTrainingEvent = addTrainingEventChannel.receiveAsFlow()

    private val addExerciseEventChannel = Channel<Result<Boolean>>()
    val addExerciseEvent = addExerciseEventChannel.receiveAsFlow()

    var trainingFormState by mutableStateOf(TrainingFormState())
    var exerciseFormState by mutableStateOf(ExerciseFormState())

    fun onTrainingDataEvent(event: TrainingFormEvent) {
        when (event) {
            is TrainingFormEvent.OnNameChanged -> {
                trainingFormState = trainingFormState.copy(name = event.name)
            }

            is TrainingFormEvent.OnDaysOfWeekChanged -> {
                trainingFormState = trainingFormState.copy(daysOfWeek = event.daysOfWeek)
            }

            is TrainingFormEvent.OnTrainingStartTimeChanged -> {
                trainingFormState = trainingFormState.copy(trainingStartTime = event.trainingStartTime)
            }

            is TrainingFormEvent.OnTrainingEndTimeChanged -> {
                trainingFormState = trainingFormState.copy(trainingEndTime = event.trainingEndTime)
            }

            is TrainingFormEvent.OnTrainingTypeChanged -> {
                trainingFormState = trainingFormState.copy(trainingType = event.trainingType)
            }

            is TrainingFormEvent.OnExercisesChanged -> {
                trainingFormState = trainingFormState.copy(exercises = event.exercises)
            }

            is TrainingFormEvent.OnSubmit -> {

            }
        }
    }

    fun onExerciseDataEvent(event: ExerciseFormEvent) {
        when (event) {
            is ExerciseFormEvent.OnNameChanged -> {
                exerciseFormState = exerciseFormState.copy(name = event.name)
            }

            is ExerciseFormEvent.OnSeriesChanged -> {
                exerciseFormState = exerciseFormState.copy(series = event.series)
            }

            is ExerciseFormEvent.OnRepetitionsChanged -> {
                exerciseFormState = exerciseFormState.copy(repetitions = event.repetitions)
            }

            is ExerciseFormEvent.OnCaloriesChanged -> {
                exerciseFormState = exerciseFormState.copy(calories = event.calories)
            }

            is ExerciseFormEvent.OnSubmit -> {

            }
        }
    }



}
