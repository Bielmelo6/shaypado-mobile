package com.ufape.shaypado.ui.screens.trainer.importFriends

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ufape.shaypado.data.repositories.interfaces.ITrainerRepository
import com.ufape.shaypado.ui.model.FriendsData
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
    val repository: ITrainerRepository
) : ViewModel(){
    var selectedFriends = mutableStateOf<List<String>>(listOf())  //mutableListOf<Int>()

    private val _friendsData = Channel<Result<FriendsData>>()
    val friendsData = _friendsData.receiveAsFlow()


    init {
        fetchFriends()
    }

    fun toggleFriend(key: String){
        var friends = selectedFriends.value
        friends = if (key in friends){
            friends.filterNot { it == key }
        }else{
            friends + key
        }
        selectedFriends.value = friends
    }

    fun fetchFriends(){
        viewModelScope.launch {
            _friendsData.send(Result.Loading)

            val result = handler.makeSafeApiCall {
                repository.fetchFriends()
            }
            _friendsData.send(result)
        }
    }

    fun isSelected(key: String): Boolean {
        return (key in selectedFriends.value)
    }


}