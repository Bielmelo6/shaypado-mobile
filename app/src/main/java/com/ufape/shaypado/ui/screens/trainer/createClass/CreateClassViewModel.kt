package com.ufape.shaypado.ui.screens.trainer.createClass

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormEvent
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler
) : ViewModel() {
    var numberOfClasses by mutableIntStateOf(0)

    var selectedClass by mutableIntStateOf(0)

    var classData by mutableStateOf<List<ClassFormState>>(listOf())


    fun onClassDataEvent(event: ClassFormEvent) {
        when (event) {
            is ClassFormEvent.OnDaysOfWeekChanged -> {
                val newTrainingsData = classData.toMutableList()
                newTrainingsData[selectedClass] =
                    newTrainingsData[selectedClass].copy(daysOfWeek = event.daysOfWeek)
                classData = newTrainingsData
            }

            is ClassFormEvent.OnEndingTimeChanged -> {
                val newTrainingsData = classData.toMutableList()
                newTrainingsData[selectedClass] =
                    newTrainingsData[selectedClass].copy(endingTime = event.endingTime)
                classData = newTrainingsData
            }

            is ClassFormEvent.OnNameChanged -> {
                val newTrainingsData = classData.toMutableList()
                newTrainingsData[selectedClass] =
                    newTrainingsData[selectedClass].copy(name = event.name)
                classData = newTrainingsData
            }

            is ClassFormEvent.OnStartingTimeChanged -> {
                val newTrainingsData = classData.toMutableList()
                newTrainingsData[selectedClass] =
                    newTrainingsData[selectedClass].copy(startingTime = event.startingTime)
                classData = newTrainingsData
            }

            is ClassFormEvent.RemoveCurrentClass -> {
                if (classData.size > 1) {
                    numberOfClasses--
                    val newTrainingsData = classData.toMutableList()
                    newTrainingsData.removeAt(selectedClass)
                    classData = newTrainingsData

                    if (selectedClass > 0) {
                        selectedClass--
                    }
                }
            }

            ClassFormEvent.OnSubmit -> TODO()
        }
    }

    fun allocateClasses() {
        numberOfClasses++
        classData = List(numberOfClasses) { ClassFormState() }
    }

    fun increaseClasses() {
        numberOfClasses++
    }

    fun decreaseClasses() {
        numberOfClasses--
    }

    fun increaseSelectedClass() {
        selectedClass++
    }

    fun decreaseSelectedClass() {
        selectedClass--
    }
}