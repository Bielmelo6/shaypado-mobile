package com.ufape.shaypado.ui.screens.trainer.friends

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.IFriendRepository
import com.ufape.shaypado.ui.model.FriendsState
import com.ufape.shaypado.util.ISafeNetworkHandler
import com.ufape.shaypado.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    val handler: ISafeNetworkHandler,
    val friendsRepository: IFriendRepository
) : ViewModel() {

    private val _friendsData = Channel<Result<FriendsState>>()
    val userProfileData = _friendsData.receiveAsFlow()

    var selectedFriendToRemove by mutableStateOf(RemoveFriendFormState())
    var selectedFriendToAdd by mutableStateOf(FriendshipCodeFormState())

    private val _addFriendEvent = Channel<Result<Unit>>()
    val addFriendEvent = _addFriendEvent.receiveAsFlow()

    private val _removeFriendEvent = Channel<Result<Unit>>()
    val removeFriendEvent = _removeFriendEvent.receiveAsFlow()

    fun fetchFriends() {
        viewModelScope.launch {
            _addFriendEvent.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                friendsRepository.getFriends()
            }

            _friendsData.send(result)
        }
    }

    fun addFriend() {
        viewModelScope.launch {
            _removeFriendEvent.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                friendsRepository.addFriend(selectedFriendToAdd.toRequest())
            }

            if (result is Result.Success) {
                fetchFriends()
            }

            _addFriendEvent.send(result)
        }
    }

    fun removeFriend() {
        viewModelScope.launch {
            val result = handler.makeSafeApiCall {
                friendsRepository.removeFriend(selectedFriendToRemove.toRequest())
            }

            if (result is Result.Success) {
                fetchFriends()
            }

            _removeFriendEvent.send(result)
        }
    }

    fun selectFriendToRemove(friendshipCode: String, name: String) {
        selectedFriendToRemove = RemoveFriendFormState(
            friendshipCode = friendshipCode,
            name = name
        )
    }

    fun selectedFriendToAdd(friendshipCode: String) {
        this.selectedFriendToAdd = FriendshipCodeFormState(friendshipCode)
    }
}