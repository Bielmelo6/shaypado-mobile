package com.ufape.shaypado.ui.screens.trainer.classDetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IClassRepository
import com.ufape.shaypado.ui.model.ClassState
import com.ufape.shaypado.ui.model.toUpdateRequest
import com.ufape.shaypado.ui.screens.trainer.createClass.ClassFormEvent
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ufape.shaypado.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class EditClassViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val classRepository: IClassRepository
) : ViewModel() {

    val _classChannel = Channel<Result<Unit>>()
    val classEvent = _classChannel.receiveAsFlow()

    val _classUpdateChannel = Channel<Result<Unit>>()
    val classUpdateEvent = _classUpdateChannel.receiveAsFlow()

    var classInfo by mutableStateOf(ClassState())

    fun fetchClassInfo(id : String) {
        viewModelScope.launch {
            _classChannel.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                classRepository.getClass(id)
            }

            if (result is Result.Success) {
                classInfo = result.data
                _classChannel.send(Result.Success(Unit))
            } else if (result is Result.Error) {
                _classChannel.send(result)
            }
        }
    }

    fun updateClass() {
        viewModelScope.launch {
            _classUpdateChannel.send(Result.Loading)
            val result = handler.makeSafeApiCall {
                classRepository.updateClass(classInfo.toUpdateRequest())
            }

            if (result is Result.Success) {
                classInfo = result.data
                _classUpdateChannel.send(Result.Success(Unit))
            } else if (result is Result.Error) {
                _classUpdateChannel.send(result)
            }
        }
    }

    fun onClassEvent(event : ClassFormEvent) {
        when (event) {
            is ClassFormEvent.OnNameChanged -> {
                classInfo = classInfo.copy(name = event.name)
            }

            is ClassFormEvent.OnDaysOfWeekChanged -> {
                classInfo = classInfo.copy(daysOfWeek = event.daysOfWeek)

            }
            is ClassFormEvent.OnEndingTimeChanged ->  {
                classInfo = classInfo.copy(endTime = event.endingTime)
            }
            is ClassFormEvent.OnStartingTimeChanged ->  {
                classInfo = classInfo.copy(startTime = event.startingTime)
            }
            ClassFormEvent.OnSubmit ->  {
                updateClass()
            }
            else -> {}
        }
    }
}