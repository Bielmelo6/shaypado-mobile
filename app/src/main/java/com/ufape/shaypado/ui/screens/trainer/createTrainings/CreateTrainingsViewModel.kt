package com.ufape.shaypado.ui.screens.trainer.createTrainings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.data.repositories.interfaces.IWorkoutRepository
import com.ufape.shaypado.ui.model.FriendsData
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTrainingsViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val repository: IWorkoutRepository
) : ViewModel() {
    var numberOfTrainings by mutableIntStateOf(0)

    var selectedTraining by mutableIntStateOf(0)

    var trainingsData by mutableStateOf<List<TrainingsFormState>>(listOf())
    var exerciseData by mutableStateOf(ExerciseFormState())

    private val _trainingRequestStatus = Channel<Result<Unit>>()
    val trainingRequestStatus = _trainingRequestStatus.receiveAsFlow()

    private fun createTraining() {
        viewModelScope.launch {
            _trainingRequestStatus.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                repository.createWorkouts(trainingsData.map { it.toRequest() })
            }

            result.fold(
                onSuccess = { _trainingRequestStatus.send(Result.Success(Unit)) },
                onFailure = {
                    _trainingRequestStatus.send(Result.Error(it)) }
            )
        }
    }

    fun onTrainingDataEvent(event: TrainingsFormEvent) {
        when (event) {
            is TrainingsFormEvent.OnCategoryChanged -> {
                val newTrainingsData = trainingsData.toMutableList()
                newTrainingsData[selectedTraining] =
                    newTrainingsData[selectedTraining].copy(category = event.category)
                trainingsData = newTrainingsData
            }

            is TrainingsFormEvent.OnExercisesChanged -> {
                val newTrainingsData = trainingsData.toMutableList()
                newTrainingsData[selectedTraining] =
                    newTrainingsData[selectedTraining].copy(exercises = event.exercises)
                trainingsData = newTrainingsData
            }

            is TrainingsFormEvent.OnNameChanged -> {
                val newTrainingsData = trainingsData.toMutableList()
                newTrainingsData[selectedTraining] =
                    newTrainingsData[selectedTraining].copy(name = event.name)
                trainingsData = newTrainingsData

            }

            TrainingsFormEvent.RemoveCurrentTraining -> {
                if (trainingsData.size > 1) {
                    numberOfTrainings--
                    val newTrainingsData = trainingsData.toMutableList()
                    newTrainingsData.removeAt(selectedTraining)
                    trainingsData = newTrainingsData

                    if (selectedTraining > 0) {
                        selectedTraining--
                    }
                }
            }

            TrainingsFormEvent.OnSubmit -> {
                createTraining()
            }

        }
    }

    fun onExerciseEvent(event: ExerciseFormEvent) {
        when (event) {
            is ExerciseFormEvent.OnDescriptionChanged -> {
                exerciseData = exerciseData.copy(description = event.description)
            }

            is ExerciseFormEvent.OnRepetitionsChanged -> {
                exerciseData = exerciseData.copy(repetitions = event.repetitions)
            }

            is ExerciseFormEvent.OnSeriesChanged -> {
                exerciseData = exerciseData.copy(series = event.series)
            }

            is ExerciseFormEvent.OnTimeChanged -> {
                exerciseData = exerciseData.copy(time = event.time)
            }

            is ExerciseFormEvent.OnTitleChanged -> {
                exerciseData = exerciseData.copy(title = event.title)
            }

            is ExerciseFormEvent.OnVideoUrlChanged -> {
                exerciseData = exerciseData.copy(videoUrl = event.videoUrl)
            }

            is ExerciseFormEvent.RemoveCurrentExercise -> {
                val newTrainingsData = trainingsData.mapIndexed { index, training ->
                    if (index == selectedTraining) {
                        training.copy(exercises = training.exercises.toMutableList().apply {
                            removeAt(event.index)
                        })
                    } else {
                        training
                    }
                }
                trainingsData = newTrainingsData
            }

            ExerciseFormEvent.OnSubmit -> {
                val updatedTrainingData = trainingsData.mapIndexed { index, training ->
                    if (index == selectedTraining) {
                        training.copy(exercises = training.exercises + exerciseData)
                    } else {
                        training
                    }
                }
                trainingsData = updatedTrainingData
            }
        }
    }

    fun allocateTrainings() {
        numberOfTrainings++
        trainingsData = List(numberOfTrainings) { TrainingsFormState() }
    }

    fun increaseTrainings() {
        numberOfTrainings++
    }

    fun decreaseTrainings() {
        numberOfTrainings--
    }

    fun increaseSelectedTraining() {
        selectedTraining++
    }

    fun decreaseSelectedTraining() {
        selectedTraining--
    }
}