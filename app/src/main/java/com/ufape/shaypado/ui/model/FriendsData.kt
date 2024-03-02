package com.ufape.shaypado.ui.model

data class FriendsData(
    val friendshipCode: String,
    val friends: List<FriendData>
)

data class FriendData(
    val friendshipCode: String,
    val name: String,
)