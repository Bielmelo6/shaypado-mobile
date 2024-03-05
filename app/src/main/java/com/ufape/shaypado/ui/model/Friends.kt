package com.ufape.shaypado.ui.model

data class FriendsState(
    val friendshipCode: String,
    val friends: List<FriendState>
)

data class FriendState(
    var isSelected: Boolean = false,
    val friendshipCode: String,
    val name: String,
)