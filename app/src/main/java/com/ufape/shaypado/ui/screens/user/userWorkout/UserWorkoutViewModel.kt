package com.ufape.shaypado.ui.screens.user.userWorkout

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IUserRepository
import com.ufape.shaypado.data.repositories.interfaces.IWorkoutRepository
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserWorkoutViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val userRepository: IUserRepository,
    private val workoutRepository: IWorkoutRepository,
) : ViewModel() {

    private var workoutId by mutableStateOf("")

    private val _workoutData = Channel<Result<WorkoutState>>()
    val workoutData = _workoutData.receiveAsFlow()

    private val _workoutConclude = Channel<Result<Unit>>()
    val workoutConclude = _workoutConclude.receiveAsFlow()

    private val _exerciseUndo = Channel<Result<Unit>>()
    val exerciseUndo = _exerciseUndo.receiveAsFlow()

    private val _exerciseConclude = Channel<Result<Unit>>()
    val exerciseConclude = _exerciseConclude.receiveAsFlow()

    fun fetchWorkout(id : String) {
        workoutId = id
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                workoutRepository.getWorkout(id)
            }

            _workoutData.send(result)
        }
    }

    fun concludeWorkout(id : String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                userRepository.concludeWorkout(id)
            }

            _workoutConclude.send(result)
            fetchWorkout(id)
        }
    }

    fun undoExercise(id : String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                userRepository.undoExercise(id)
            }

            _exerciseUndo.send(result)
            fetchWorkout(workoutId)
        }
    }

    fun concludeExercise(id : String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                userRepository.concludeExercise(id)
            }

            _exerciseConclude.send(result)

            fetchWorkout(workoutId)
        }
    }
}