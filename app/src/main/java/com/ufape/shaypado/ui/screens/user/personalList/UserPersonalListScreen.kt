package com.ufape.shaypado.ui.screens.user.personalList

import CardPersonalList
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.screens.shimmers.ErrorScreen
import com.ufape.shaypado.ui.screens.shimmers.TrainerHomeShimmer
import com.ufape.shaypado.util.Result

@Composable
fun UserPersonalListScreen(navController: NavController) {

    val viewModel = hiltViewModel<PersonalListViewModel>()

    var personalsData = viewModel.personalsData.collectAsState(initial = Result.Loading)

    LaunchedEffect(Unit) {
        viewModel.fetchPersonals()
    }

    if (personalsData.value is Result.Error) {
        return ErrorScreen {
            viewModel.fetchPersonals()
        }
    }

    if (personalsData.value is Result.Loading) {
        return TrainerHomeShimmer()
    }

    val personals = (personalsData.value as Result.Success).data


    AppHeader(navController = navController, title = R.string.personal_title)

    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn {
        items(personals.size) { index ->
            CardPersonalList(
                navController = navController,
                personals[index]
            )
        }
    }
}