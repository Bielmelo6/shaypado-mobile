package com.ufape.shaypado.ui.screens.trainer.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppDialog
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.Chip
import com.ufape.shaypado.ui.components.ChipSelectionGroup
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.copyToClipboard
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun FriendsScreen(
    navController: NavController,
    showSnackbar : (String) -> Unit
) {
    val friendsViewModel = hiltViewModel<FriendsViewModel>()
    val friendsData by friendsViewModel.userProfileData.collectAsState(
        initial = Result.Loading
    )

    val context = LocalContext.current

    var showAddFriendDialogType by remember { mutableStateOf(false) }
    var showAddFriendDialog by remember { mutableStateOf(false) }
    var showRemoveFriendDialog by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        friendsViewModel.fetchFriends()
    }

    LaunchedEffect(key1 = friendsViewModel.addFriendEvent) {
        friendsViewModel.addFriendEvent.collect {
            if (it is Result.Success) {
                showSnackbar("Amigo adicionado com sucesso")
            } else if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
            }
            showAddFriendDialog = false
            showAddFriendDialogType = false
        }
    }

    LaunchedEffect(key1 = friendsViewModel.removeFriendEvent) {
        friendsViewModel.removeFriendEvent.collect {
            if (it is Result.Success) {
                showSnackbar("Amigo removido com sucesso")
            } else if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
            }
        }
    }

    if (friendsData is Result.Loading)
        return AppText(
            text = "Carregando...",
            textType = TextType.TITLE_MEDIUM
        )

    if (friendsData is Result.Error)
        return AppText(
            text = (friendsData as Result.Error).exception.getErrorMessage(context),
            textType = TextType.TITLE_MEDIUM
        )

    val friends = (friendsData as Result.Success).data

    AppHeader(
        navController = navController,
        trailingContent = {
            AddButton(
                variant = ButtonVariant.SECONDARY_CONTAINER,
                onClick = {
                    showAddFriendDialogType = true
                }
            )
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier
            .fillMaxHeight(0.7f)
    ) {
        LazyColumn {
            items(friends.friends.size) { index ->
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        Image(
                            modifier = Modifier.size(50.dp),
                            painter = painterResource(id = R.drawable.ic_student),
                            contentDescription = "User pet"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            AppText(
                                text = friends.friends[index].name,
                                textType = TextType.TITLE_MEDIUM
                            )
                        }
                    }

                    FilledIconButton(
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            friendsViewModel.selectFriendToRemove(
                                friends.friends[index].friendshipCode,
                                friends.friends[index].name
                            )
                            showRemoveFriendDialog = true
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_heart_filled),
                            contentDescription = "Heart",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()

            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Column(
        modifier = Modifier.fillMaxSize(1f),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(139.dp)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .clickable {
                    context.copyToClipboard(
                        label = "Código de amizade",
                        text = friends.friendshipCode
                    )
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = "Seu código de amizade é",
                textType = TextType.BODY_MEDIUM,
            )

            Spacer(modifier = Modifier.height(16.dp))
            AppText(
                text = friends.friendshipCode,
                textType = TextType.DISPLAY_LARGE,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }

    AppDialog(
        onDismiss = { showAddFriendDialog = false },
        isDialogVisible = showAddFriendDialog
    ) {

        CustomTextField(
            label = R.string.friendship_code,
            value = friendsViewModel.selectedFriendToAdd.friendshipCode,
            errorMessage = friendsViewModel.selectedFriendToAdd.friendshipCodeError,
            onValueChange = {
                friendsViewModel.selectedFriendToAdd(it)
            },
            placeholder = R.string.friendship_code_placehold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = R.string.add,
            onClick = {
                friendsViewModel.addFriend()
            },
        )


    }

    AppDialog(
        onDismiss = { showAddFriendDialogType = false },
        isDialogVisible = showAddFriendDialogType
    ) {
        val chips = listOf(
            Chip(
                value = "0",
                icon = R.drawable.common_chip,
                label = R.string.use_friendship_code,
                onClick = {
                    showAddFriendDialog = true
                }
            ),
            Chip(
                value = "1",
                icon = R.drawable.personal_chip,
                label = R.string.create_users,
                onClick = {
                    showAddFriendDialogType = false
                    navController.navigate(TrainerNavigationScreen.CreateUsers.route)
                }
            )
        )
        Column {
            ChipSelectionGroup(
                chips = chips,
                onChipSelected = {},
            )
        }
    }

    AppDialog(
        onDismiss = { showRemoveFriendDialog = false },
        isDialogVisible = showRemoveFriendDialog
    ) {
        Column {
            AppText(
                textAlignment = TextAlign.Center,
                text = "Deseja remover ${friendsViewModel.selectedFriendToRemove.name} da sua lista de amigos?",
                textType = TextType.TITLE_MEDIUM
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                text = R.string.cancel,
                onClick = {
                    showRemoveFriendDialog = false
                },
                variant = ButtonVariant.SECONDARY_CONTAINER
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                text = R.string.remove,
                onClick = {
                    friendsViewModel.removeFriend()
                    showRemoveFriendDialog = false
                }
            )
        }
    }
}