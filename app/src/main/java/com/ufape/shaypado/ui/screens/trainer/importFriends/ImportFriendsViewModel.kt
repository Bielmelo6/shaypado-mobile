package com.ufape.shaypado.ui.screens.trainer.importFriends

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IFriendRepository
import com.ufape.shaypado.ui.model.FriendState
import com.ufape.shaypado.ui.model.FriendsState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImportFriendsViewModel @Inject constructor(
    private val handler: ISafeNetworkHandler,
    private val friendsRepository: IFriendRepository
) : ViewModel(){
    private val _friendsData = Channel<Result<Unit>>()
    val friendsData = _friendsData.receiveAsFlow()

    var friends by mutableStateOf<List<FriendState>>(listOf())

    fun fetchFriends() {
        viewModelScope.launch {
            _friendsData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                friendsRepository.getFriends()
            }

            if (result is Result.Success) {
                friends = result.data.friends
                _friendsData.send(Result.Success(Unit))
            }else if (result is Result.Error){
                _friendsData.send(result)
            }
        }
    }

    fun toggleFriend(key: Int){
        val newFriend = friends.toMutableList().mapIndexed { index, friend ->
            if (key == index){
                friend.copy(isSelected = !friend.isSelected)
            }else {
                friend
            }
        }
        friends = newFriend
    }
}