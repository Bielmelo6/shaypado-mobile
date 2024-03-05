package com.ufape.shaypado.ui.screens.trainer.editClass

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.FriendState
import com.ufape.shaypado.util.ISafeNetworkHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditClassViewModel @Inject constructor(
    private val trainerRepository: ITrainerRepository,
    private val handler: ISafeNetworkHandler
) : ViewModel(){
    fun importFriends(friendsData: List<FriendState>) {
        val temp = friends
        friends = (temp + friendsData).distinct()
    }

    var friends by mutableStateOf<List<FriendState>>(listOf())

}
