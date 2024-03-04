package com.ufape.shaypado.data.model

import com.google.gson.annotations.SerializedName
import com.ufape.shaypado.ui.model.FriendData
import com.ufape.shaypado.ui.model.FriendsData

data class FriendsResponse(
    @SerializedName("friendship_code")
    val friendshipCode: String,
    @SerializedName("friends")
    val friends: List<FriendResponse>
)

data class FriendResponse(
    @SerializedName("friendship_code")
    val friendshipCode: String,
    @SerializedName("name")
    val name: String,
)

data class PendingFriendResponse(
    @SerializedName("friendship_code")
    val friendshipCode: String,
    @SerializedName("name")
    val name: String,
)

data class FriendshipCodeRequest (
    @SerializedName("friendship_code")
    val friendshipCode: String
)

fun FriendsResponse.toUiModel(): FriendsData {
    return FriendsData(
        friendshipCode = this.friendshipCode,
        friends = this.friends.map { it.toUiModel() }
    )
}

fun FriendResponse.toUiModel(): FriendData {
    return FriendData(
        friendshipCode = this.friendshipCode,
        name = this.name
    )
}