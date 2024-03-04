package com.ufape.shaypado.ui.screens.trainer.updateWorkout

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.UpdateExerciseRequest
import com.ufape.shaypado.data.model.UpdateWorkoutRequest
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.screens.trainer.createTrainings.ExerciseFormEvent
import com.ufape.shaypado.ui.screens.trainer.createTrainings.ExerciseFormState
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormEvent
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormState
import com.ufape.shaypado.ui.screens.trainer.createTrainings.toRequest
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
    private val trainerRepository: ITrainerRepository
) : ViewModel() {

    var workoutState by mutableStateOf(TrainingsFormState())
    var exerciseData by mutableStateOf(ExerciseFormState())
    var categoriesData by mutableStateOf<List<CategoriesState>>(emptyList())

    private val _workoutData = Channel<Result<Unit>>()
    val workoutData = _workoutData.receiveAsFlow()

    var selectedExerciseToEdit by mutableStateOf<Int?>(0)

    fun fetchWorkout(workoutId: String) {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                trainerRepository.fetchWorkout(
                    workoutId
                )
            }

            val categories = handler.makeSafeApiCall {
                trainerRepository.fetchCategories()
            }

            if (result is Result.Success && categories is Result.Success) {
                categoriesData = categories.data
                _workoutData.send(Result.Success(Unit))

                val exercises = result.data.exercises.map {
                    ExerciseFormState(
                        title = it.title,
                        description = it.description,
                        videoUrl = it.videoUrl ?: "",
                        series = it.series.toString(),
                        repetitions = it.repetitions.toString(),
                        time = it.time
                    )
                }
                workoutState = workoutState.copy(
                    name = result.data.name,
                    category = result.data.category,
                    exercises = exercises
                )
            } else {
                _workoutData.send(Result.Error(Exception("Error fetching workout")))
            }
        }
    }

    fun onWorkoutEvent(event: TrainingsFormEvent) {
        when (event) {
            is TrainingsFormEvent.OnCategoryChanged -> {
                workoutState =
                    workoutState.copy(category = event.category, categoryLabel = event.label)
            }

            is TrainingsFormEvent.OnExercisesChanged -> {
                workoutState = workoutState.copy(exercises = event.exercises)
            }

            is TrainingsFormEvent.OnNameChanged -> {
                workoutState = workoutState.copy(name = event.name)
            }

            else -> {}
        }
    }

    fun updateWorkout() {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                trainerRepository.updateWorkout(
                    UpdateWorkoutRequest(
                        id = workoutState.id!!,
                        name = workoutState.name,
                        category = workoutState.category,
                        exercises = workoutState.exercises.map { it.id!! }
                    )
                )
            }

            if (result is Result.Success) {
                _workoutData.send(Result.Success(Unit))
            } else {
                _workoutData.send(Result.Error(Exception("Error updating workout")))
            }
        }
    }

    fun createExercise() {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                trainerRepository.createExercise(
                    exerciseData.toRequest()
                )
            }

            if (result is Result.Success) {
                val newExercises = workoutState.exercises.toMutableList()
                val data = result.data
                newExercises.add(
                    ExerciseFormState(
                        id = data.id,
                        title = data.title,
                        description = data.description,
                        videoUrl = data.videoUrl ?: "",
                        series = data.series.toString(),
                        repetitions = data.repetitions.toString(),
                        time = data.time
                    )
                )
                workoutState = workoutState.copy(exercises = newExercises)
                _workoutData.send(Result.Success(Unit))
            } else if (result is Result.Error) {
                _workoutData.send(Result.Error(Exception("Error creating exercise")))
            }
        }
    }

    fun updateExercise() {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                trainerRepository.updateExercise(
                    UpdateExerciseRequest(
                        id = exerciseData.id!!,
                        title = exerciseData.title,
                        description = exerciseData.description,
                        videoUrl = exerciseData.videoUrl,
                        series = exerciseData.series.toInt(),
                        repetitions = exerciseData.repetitions.toInt(),
                        time = exerciseData.time

                    )
                )
            }

            Log.d("UpdateExercise", "Result: $result")


            if (result is Result.Success) {
                val newExercises = workoutState.exercises.toMutableList()
                newExercises.map {
                    if (it.id == exerciseData.id) {
                        it.copy(
                            title = exerciseData.title,
                            description = exerciseData.description,
                            videoUrl = exerciseData.videoUrl,
                            series = exerciseData.series,
                            repetitions = exerciseData.repetitions,
                            time = exerciseData.time
                        )
                    } else {
                        it
                    }
                }
                workoutState = workoutState.copy(exercises = newExercises)
                _workoutData.send(Result.Success(Unit))
            } else if (result is Result.Error) {
                _workoutData.send(Result.Error(Exception("Error updating exercise")))
            }


        }
    }

    fun setExerciseData(exerciseIndex: Int?) {
        if (exerciseIndex != null) {
            exerciseData = workoutState.exercises[exerciseIndex]
        }
        selectedExerciseToEdit = exerciseIndex
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

            is ExerciseFormEvent.OnSubmit -> {
                if (selectedExerciseToEdit == null) {
                    createExercise()
                } else {
                    updateExercise()
                }
            }

            else -> {}
        }
    }

    fun removeExercise() {
        if (selectedExerciseToEdit != null) {
            val newExercises = workoutState.exercises.toMutableList()
            newExercises.removeAt(selectedExerciseToEdit!!)
            workoutState = workoutState.copy(exercises = newExercises)
        }
    }

}