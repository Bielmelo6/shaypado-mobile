package com.ufape.shaypado.ui.screens.trainer.importFriends

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImportFriendsViewModel @Inject constructor(

) : ViewModel(){
    var friends by mutableStateOf<List<FriendsState>>(listOf())


    init {
        fetchFriends()
    }

    fun toggleFriend(key: Int){
        val newFriend = friends.mapIndexed { index, friend ->
            if (key == index){
                friend.copy(isSelected = !friend.isSelected)
            }else {
                friend
            }
        }
        friends = newFriend
    }

    fun fetchFriends(){
        //TODO  IMPLEMENT THIS
        for( i in 0..10){
            val friend = Friend(
                value = "Friend $i",
                title = "Friend $i",
                description = "Friend $i"
            )
            friends = friends + FriendsState(data = friend)
        }
    }
}