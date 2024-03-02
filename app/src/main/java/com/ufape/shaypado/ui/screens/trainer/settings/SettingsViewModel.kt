package com.ufape.shaypado.ui.screens.trainer.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.implementations.TrainerRepository
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.TrainerProfileData
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.ufape.shaypado.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val trainerRepository: ITrainerRepository
) : ViewModel() {

    private val _userProfileData = Channel<Result<TrainerProfileData>>()
    val userProfileData = _userProfileData.receiveAsFlow()

    fun fetchUserProfileData() {
        viewModelScope.launch {
            _userProfileData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                trainerRepository.fetchTrainerProfile()
            }

            _userProfileData.send(result)
        }
    }

}