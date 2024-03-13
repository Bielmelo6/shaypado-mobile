package com.ufape.shaypado.ui.screens.trainer.updateWorkout

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.repositories.interfaces.IExerciseRepository
import com.ufape.shaypado.data.repositories.interfaces.IWorkoutRepository
import com.ufape.shaypado.ui.model.CategoryState
import com.ufape.shaypado.ui.model.ExerciseState
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.ui.model.toRequest
import com.ufape.shaypado.ui.model.toUpdateRequest
import com.ufape.shaypado.ui.screens.trainer.createTrainings.ExerciseFormEvent
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormEvent
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateWorkoutViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val workoutRepository: IWorkoutRepository,
    private val exerciseRepository: IExerciseRepository
) : ViewModel() {
    var workoutState by mutableStateOf(WorkoutState())
    var createExerciseData by mutableStateOf(ExerciseState())
    var editExerciseData by mutableStateOf(ExerciseState())
    var categoriesData by mutableStateOf<List<CategoryState>>(emptyList())

    private val _workoutData = Channel<Result<WorkoutState>>()
    val workoutData = _workoutData.receiveAsFlow()

    private val _workoutUpdateEvent = Channel<Result<Unit>>()
    val workoutUpdateEvent = _workoutUpdateEvent.receiveAsFlow()

    fun fetchWorkout(workoutId: String) {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                workoutRepository.getWorkout(
                    workoutId
                )
            }

            val categories = handler.makeSafeApiCall {
                workoutRepository.fetchWorkoutCategories()
            }

            if (result is Result.Success && categories is Result.Success) {
                categoriesData = categories.data
                _workoutData.send(Result.Success(result.data))

                workoutState = workoutState.copy(
                    name = result.data.name,
                    category = result.data.category,
                    exercises = result.data.exercises
                )
            } else {
                _workoutData.send(result)
            }
        }
    }

    fun onWorkoutEvent(event: TrainingsFormEvent) {
        when (event) {
            is TrainingsFormEvent.OnCategoryChanged -> {
                workoutState =
                    workoutState.copy(category = event.category)
            }

            is TrainingsFormEvent.OnExercisesChanged -> {
                workoutState = workoutState.copy(exercises = event.exercises)
            }

            is TrainingsFormEvent.OnNameChanged -> {
                workoutState = workoutState.copy(name = event.name)
            }

            is TrainingsFormEvent.OnSubmit -> {
                updateWorkout()
            }

            else -> {}
        }
    }

    fun updateWorkout() {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                workoutRepository.updateWorkout(
                    workoutState.toUpdateRequest()
                )
            }

            if (result is Result.Success) {
                _workoutUpdateEvent.send(Result.Success(Unit))
            } else {
                _workoutUpdateEvent.send(result as Result.Error)
            }
        }
    }

    fun createExercise() {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                exerciseRepository.addExercise(
                    createExerciseData.toRequest()
                )
            }

            if (result is Result.Success) {
                val newExercises = workoutState.exercises.toMutableList()
                newExercises.add(
                    result.data
                )
                workoutState = workoutState.copy(exercises = newExercises)
            }
        }
    }

    private fun updateExercise() {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                exerciseRepository.updateExercise(
                   editExerciseData.toUpdateRequest()
                )
            }

            if (result is Result.Success) {
                val data = result.data
                val newExercises = workoutState.exercises.toMutableList().map {
                    if (it.id == data.id) {
                        data
                    } else {
                        it
                    }
                }
                workoutState = workoutState.copy(exercises = newExercises)
            } else if (result is Result.Error) {
                _workoutData.send(result)
            }
        }
    }

    fun setExerciseData(exerciseIndex: Int) {
        editExerciseData = workoutState.exercises[exerciseIndex]
    }

    fun onEditExerciseEvent(event: ExerciseFormEvent) {
        when (event) {
            is ExerciseFormEvent.OnDescriptionChanged -> {
                editExerciseData = editExerciseData.copy(description = event.description)
            }

            is ExerciseFormEvent.OnRepetitionsChanged -> {
                editExerciseData = editExerciseData.copy(repetitions = event.repetitions)
            }

            is ExerciseFormEvent.OnSeriesChanged -> {
                editExerciseData = editExerciseData.copy(series = event.series)
            }

            is ExerciseFormEvent.OnTimeChanged -> {
                editExerciseData = editExerciseData.copy(time = event.time)
            }

            is ExerciseFormEvent.OnTitleChanged -> {
                editExerciseData = editExerciseData.copy(title = event.title)
            }

            is ExerciseFormEvent.OnVideoUrlChanged -> {
                editExerciseData = editExerciseData.copy(videoUrl = event.videoUrl)
            }

            is ExerciseFormEvent.OnCategoryChanged -> {
                editExerciseData = editExerciseData.copy(category = event.category, categoryId = event.id)
            }

            is ExerciseFormEvent.OnSubmit -> {
                updateExercise()
            }

            else -> {}
        }
    }

    fun onCreateExerciseEvent(event: ExerciseFormEvent) {
        when (event) {
            is ExerciseFormEvent.OnDescriptionChanged -> {
                createExerciseData = createExerciseData.copy(description = event.description)
            }

            is ExerciseFormEvent.OnRepetitionsChanged -> {
                createExerciseData = createExerciseData.copy(repetitions = event.repetitions)
            }

            is ExerciseFormEvent.OnSeriesChanged -> {
                createExerciseData = createExerciseData.copy(series = event.series)
            }

            is ExerciseFormEvent.OnTimeChanged -> {
                createExerciseData = createExerciseData.copy(time = event.time)
            }

            is ExerciseFormEvent.OnTitleChanged -> {
                createExerciseData = createExerciseData.copy(title = event.title)
            }

            is ExerciseFormEvent.OnVideoUrlChanged -> {
                createExerciseData = createExerciseData.copy(videoUrl = event.videoUrl)
            }

            is ExerciseFormEvent.OnSubmit -> {
                createExercise()
            }

            else -> {}
        }
    }

    fun removeExercise() {
        val newExercises =
            workoutState.exercises.toMutableList().mapIndexed { index, exerciseFormState ->
                if (exerciseFormState.id == editExerciseData.id) {
                    null
                } else {
                    exerciseFormState
                }
            }.filterNotNull()
        workoutState = workoutState.copy(exercises = newExercises)
    }
}