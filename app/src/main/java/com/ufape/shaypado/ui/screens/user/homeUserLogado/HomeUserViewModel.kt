package com.ufape.shaypado.ui.screens.user.homeUserLogado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IUserRepository
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeUserViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _workoutsData = Channel<Result<List<WorkoutState>>>()
    val workoutsData = _workoutsData.receiveAsFlow()


    fun fetchWorkouts() {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                userRepository.fetchWorkouts()
            }

            _workoutsData.send(result)
        }
    }

}