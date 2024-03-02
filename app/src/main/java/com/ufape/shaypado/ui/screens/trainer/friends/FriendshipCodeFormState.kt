package com.ufape.shaypado.ui.screens.trainer.friends

import androidx.annotation.StringRes
import com.ufape.shaypado.data.model.FriendshipCodeRequest

data class FriendshipCodeFormState(
    val friendshipCode: String = "",
    @StringRes val friendshipCodeError: Int? = null,
)

data class RemoveFriendFormState(
    val friendshipCode: String = "",
    @StringRes val friendshipCodeError: Int? = null,
    val name : String = "",
)

fun FriendshipCodeFormState.toRequest(): FriendshipCodeRequest {
    return FriendshipCodeRequest(friendshipCode)
}

fun RemoveFriendFormState.toRequest(): FriendshipCodeRequest {
    return FriendshipCodeRequest(friendshipCode)
}