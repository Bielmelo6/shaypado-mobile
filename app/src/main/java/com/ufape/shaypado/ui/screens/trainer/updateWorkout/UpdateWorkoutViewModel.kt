package com.ufape.shaypado.ui.screens.trainer.updateWorkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.WorkoutData
import com.ufape.shaypado.ui.screens.trainer.createTrainings.ExerciseFormEvent
import com.ufape.shaypado.ui.screens.trainer.createTrainings.ExerciseFormState
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormEvent
import com.ufape.shaypado.ui.screens.trainer.createTrainings.TrainingsFormState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateWorkoutViewModel @Inject constructor(
    private val handler : ISafeNetworkHandler,
    private val trainerRepository : ITrainerRepository
) : ViewModel() {

    var workoutState by mutableStateOf(TrainingsFormState())
    var exerciseData by mutableStateOf(ExerciseFormState())

    private val _workoutData = Channel<Result<WorkoutData>>()
    val workoutData = _workoutData.receiveAsFlow()

    fun fetchWorkout(workoutId: String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                trainerRepository.fetchWorkout(
                    workoutId
                )
            }

            _workoutData.send(result)
            if (result is Result.Success) {
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
                workoutState =  workoutState.copy(
                    name = result.data.name,
                    category = result.data.category,
                    exercises = exercises
                )
            }
        }
    }

    fun onWorkoutEvent(event: TrainingsFormEvent) {
        when (event) {
            is TrainingsFormEvent.OnCategoryChanged -> {

            }

            is TrainingsFormEvent.OnExercisesChanged -> {

            }

            is TrainingsFormEvent.OnNameChanged -> {

            }

            TrainingsFormEvent.OnSubmit -> {
            }

            else -> {}

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

            ExerciseFormEvent.OnSubmit -> {

            }
            else -> {}
        }
    }
}