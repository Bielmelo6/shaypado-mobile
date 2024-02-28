package com.ufape.shaypado.ui.screens.trainer.createTrainings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTrainingsViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler
) : ViewModel() {
    var numberOfTrainings by mutableIntStateOf(0)

    var selectedTraining by mutableIntStateOf(0)

    var trainingsData by mutableStateOf<List<TrainingsFormState>>(listOf())


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

            TrainingsFormEvent.OnSubmit -> TODO()

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