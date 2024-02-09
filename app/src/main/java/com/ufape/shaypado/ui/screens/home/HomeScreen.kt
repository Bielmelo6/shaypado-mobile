package com.ufape.shaypado.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.ufape.shaypado.ui.components.DaysOfWeekChooser
import com.ufape.shaypado.ui.components.TimePicker
import com.ufape.shaypado.ui.screens.addTraining.AddTrainingScreen

@Composable
fun HomeScreen(navController: NavController) {
    AddTrainingScreen()
}