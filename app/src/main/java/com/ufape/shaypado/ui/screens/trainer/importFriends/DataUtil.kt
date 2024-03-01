package com.ufape.shaypado.ui.screens.trainer.importFriends

data class Friend(
    val value: String = "",
    val title: String = "",
    val description: String = "",
)

data class FriendsState(
    var isSelected: Boolean = false,
    var data: Friend
)