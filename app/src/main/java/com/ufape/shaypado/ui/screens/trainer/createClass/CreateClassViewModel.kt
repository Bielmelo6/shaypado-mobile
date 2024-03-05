package com.ufape.shaypado.ui.screens.trainer.createClass

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IClassRepository
import com.ufape.shaypado.ui.model.ClassState
import com.ufape.shaypado.ui.model.FriendState
import com.ufape.shaypado.ui.model.toCreateRequest
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormEvent
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateClassViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val repository: IClassRepository
) : ViewModel() {
    var numberOfClasses by mutableIntStateOf(0)

    var selectedClass by mutableIntStateOf(0)

    var classData by mutableStateOf<List<ClassState>>(listOf())

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
                    newTrainingsData[selectedClass].copy(endTime = event.endingTime)
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
                    newTrainingsData[selectedClass].copy(startTime = event.startingTime)
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

            ClassFormEvent.OnSubmit ->  {
                createClasses()
            }
        }
    }

    fun importFriends(friends : List<FriendState>){
        var newData = classData.toMutableList()[selectedClass].students
        newData = newData.union(friends).toMutableList()
        classData = classData.mapIndexed { index, classState ->
            if (index == selectedClass){
                classState.copy(students = newData)
            }else {
                classState
            }
        }
    }

    fun removeFriend(friend: Int){
        val newData = classData[selectedClass].students.toMutableList()
        newData.removeAt(friend)
        classData = classData.mapIndexed { index, classState ->
            if (index == selectedClass){
                classState.copy(students = newData)
            }else {
                classState
            }
        }
    }

    fun createClasses() {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                repository.addClasses(classData.map { it.toCreateRequest() })
            }

        }
    }

    fun allocateClasses() {
        numberOfClasses++
        classData = List(numberOfClasses) { ClassState() }
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