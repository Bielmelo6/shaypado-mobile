package com.ufape.shaypado.ui.screens.trainer.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IClassRepository
import com.ufape.shaypado.ui.model.ClassState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainerHomeViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val classRepository : IClassRepository
) : ViewModel() {
    private val _classesData = Channel<Result<Unit>>()
    val classesData = _classesData.receiveAsFlow()

    var classes by mutableStateOf<List<ClassState>>(listOf())

    fun fetchClasses () {
        viewModelScope.launch {
            _classesData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                classRepository.getClasses()
            }

            if (result is Result.Success) {
                classes = result.data
                _classesData.send(Result.Success(Unit))
            }else if (result is Result.Error){
                _classesData.send(result)
            }
        }
    }




}