package com.ufape.shaypado.ui.screens.trainer.workouts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.model.WorkoutIdRequest
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.WorkoutData
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val trainerRepository : ITrainerRepository
) : ViewModel() {

    private val _workoutsData = Channel<Result<List<WorkoutData>>>()
    val workoutsData = _workoutsData.receiveAsFlow()

    var selectedWorkoutToRemove by mutableStateOf<String?>(null)

    private val _removeWorkoutChannel = Channel<Result<Unit>>()
    val removeWorkoutEvent = _removeWorkoutChannel.receiveAsFlow()

    fun fetchWorkouts() {
        viewModelScope.launch {
            _workoutsData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                trainerRepository.fetchWorkouts()
            }

            _workoutsData.send(result)
        }
    }

    fun deleteWorkout() {
        if (selectedWorkoutToRemove == null) return
        viewModelScope.launch {
            val workoutId = selectedWorkoutToRemove ?: return@launch
            val result = handler.makeSafeApiCall {
                trainerRepository.deleteWorkout(
                    WorkoutIdRequest(workoutId)
                )
            }

            _removeWorkoutChannel.send(result)
            if (result is Result.Success) {
                fetchWorkouts()
            }
        }
    }

    fun selectWorkoutToRemove(workoutId: String?) {
        selectedWorkoutToRemove = workoutId
    }
}