package com.ufape.shaypado.ui.screens.trainer.friends

import androidx.lifecycle.ViewModel
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    val handler: ISafeNetworkHandler,
    val trainerRepository : ITrainerRepository
) : ViewModel() {

}