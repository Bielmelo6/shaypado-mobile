package com.ufape.shaypado.ui.screens.trainer.importFriends

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.FriendState
import com.ufape.shaypado.util.Result

@Composable
fun ImportFriendsScreen(
    navController: NavController,
    onImport: (List<FriendState>) -> Unit
) {
    val importFriendsViewModel = hiltViewModel<ImportFriendsViewModel>()
    val isLoading = importFriendsViewModel.friendsData.collectAsState(
        initial = Result.Loading
    )

    LaunchedEffect(Unit) {
        importFriendsViewModel.fetchFriends()

    }

    if (isLoading.value is Result.Loading)
        return Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            AppText(
                textType = TextType.TITLE_MEDIUM,
                text = R.string.loading,
                fillWidth = true
            )
        }

    if (isLoading.value is Result.Error)
        return Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            AppText(
                textType = TextType.TITLE_MEDIUM,
                text = R.string.error_loading_friends,
                fillWidth = true
            )
        }


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BackButton {
            navController.popBackStack()
        }

        AppText(
            textType = TextType.HEADLINE_MEDIUM,
            textAlignment = TextAlign.Center,
            text = R.string.import_friends,
            fillWidth = true
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(importFriendsViewModel.friends.size) {
            FriendCard(
                selected = importFriendsViewModel.friends[it].isSelected,
                title = importFriendsViewModel.friends[it].name,
                description = "",
                onSelected = {
                    importFriendsViewModel.toggleFriend(it)
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    AppButton(
        text = R.string.cancel,
        variant = ButtonVariant.SECONDARY_CONTAINER,
        onClick = {
            navController.popBackStack()
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    AppButton(
        text = R.string.button_import,
        variant = ButtonVariant.PRIMARY,
        onClick = {
            val selectedFriends =
                importFriendsViewModel.friends.filter { it.isSelected }
            onImport(selectedFriends)
            navController.popBackStack()
        }
    )


}

@Composable
@Preview
fun FriendCard(
    title: String = "John Doe",
    description: String = "Some description",
    selected: Boolean = true,
    onSelected: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Image(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                painter = painterResource(id = R.drawable.ic_student),
                contentDescription = "student"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.width(200.dp)
            ) {
                AppText(
                    fillWidth = true,
                    text = "John Doe",
                    textType = TextType.TITLE_MEDIUM
                )
                Spacer(modifier = Modifier.width(8.dp))
                AppText(
                    fillWidth = true,
                    text = "Some description",
                    textType = TextType.TITLE_SMALL
                )

            }
        }

        Box(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .background(
                    shape = RoundedCornerShape(8.dp),
                    color = if (selected) MaterialTheme.colorScheme.tertiaryContainer else Color.Transparent
                )
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    onSelected()
                },
        )
    }

    Spacer(modifier = Modifier.height(8.dp))
    HorizontalDivider()
}

@Preview
@Composable
fun ImportFriendsScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        ImportFriendsScreen(
            navController = rememberNavController(),
            onImport = {

            }
        )
    }
}