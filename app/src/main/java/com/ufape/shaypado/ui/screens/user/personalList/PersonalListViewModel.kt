package com.ufape.shaypado.ui.screens.user.personalList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IUserRepository
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.ui.model.WorkoutState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalListViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val userRepository: IUserRepository
) : ViewModel() {

    private val _personalsData = Channel<Result<List<TrainerProfileData>>>()
    val personalsData = _personalsData.receiveAsFlow()

    private val _personalData = Channel<Result<TrainerProfileData>>()
    val personalData = _personalData.receiveAsFlow()

    fun fetchPersonals() {
        viewModelScope.launch {
           val result = handler.makeSafeApiCall {
               userRepository.fetchProfessionals()
           }

            _personalsData.send(result)
        }
    }

    fun fetchPersonal(id: String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                userRepository.fetchProfessional(id)
            }

            _personalData.send(result)
        }
    }


}