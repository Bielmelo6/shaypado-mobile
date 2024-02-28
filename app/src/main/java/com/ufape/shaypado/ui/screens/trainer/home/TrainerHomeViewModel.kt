package com.ufape.shaypado.ui.screens.trainer.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrainerHomeViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler
) : ViewModel() {
    var classesState by mutableStateOf(ClassesState())






}