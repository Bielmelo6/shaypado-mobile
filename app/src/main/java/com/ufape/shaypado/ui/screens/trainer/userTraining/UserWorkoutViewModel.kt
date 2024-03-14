package com.ufape.shaypado.ui.screens.trainer.userTraining

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
    private val _workoutData = Channel<Result<WorkoutState>>()
    val workoutData = _workoutData.receiveAsFlow()

    fun fetchWorkout(id : String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                workoutRepository.getWorkout(id)
            }

            _workoutData.send(result)
        }
    }

    fun concludeWorkout() {
        viewModelScope.launch {
            userRepository.concludeWorkout()
        }
    }


}