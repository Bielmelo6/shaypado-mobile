package com.ufape.shaypado.ui.screens.trainer.importWorkouts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IFriendRepository
import com.ufape.shaypado.data.repositories.interfaces.IWorkoutRepository
import com.ufape.shaypado.ui.model.FriendState
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportWorkoutsViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val workoutsRepository: IWorkoutRepository
) : ViewModel(){
    private val _workoutData = Channel<Result<Unit>>()
    val workoutData = _workoutData.receiveAsFlow()

    var workouts by mutableStateOf<List<WorkoutState>>(listOf())

    fun fetchFriends() {
        viewModelScope.launch {
            _workoutData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                workoutsRepository.getWorkouts()
            }

            if (result is Result.Success) {
                workouts = result.data
                _workoutData.send(Result.Success(Unit))
            }else if (result is Result.Error){
                _workoutData.send(result)
            }
        }
    }

    fun toggleWorkout(key: Int){
        val newFriend = workouts.toMutableList().mapIndexed { index, friend ->
            if (key == index){
                friend.copy(isSelected = !friend.isSelected)
            }else {
                friend
            }
        }
        workouts = newFriend
    }
}