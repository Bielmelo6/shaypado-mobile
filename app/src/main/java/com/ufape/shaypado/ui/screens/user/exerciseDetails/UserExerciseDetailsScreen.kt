package com.ufape.shaypado.ui.screens.user.exerciseDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IExerciseRepository
import com.ufape.shaypado.ui.model.ExerciseState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserExerciseDetailsScreen @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val exerciseApi: IExerciseRepository
) : ViewModel() {

    private val _exerciseData = Channel<Result<ExerciseState>>()
    val exerciseData = _exerciseData.receiveAsFlow()

    fun fetchExerciseDetails(id: String) {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                exerciseApi.fetchExercise(id)
            }

            _exerciseData.send(result)
        }
    }

}