package com.ufape.shaypado.ui.screens.addTraining

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.DaysOfWeekChooser
import com.ufape.shaypado.ui.components.TimePicker

@Composable
fun AddTrainingScreen() {
    val viewModel: AddTrainingViewModel = viewModel()


    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.addExerciseEvent.collect {
            if (it.isSuccess) {

            } else {

            }
        }

        viewModel.addTrainingEvent.collect {
            if (it.isSuccess) {

            } else {

            }
        }
    }

    Card {
        CustomTextField(
            placeholder = R.string.input_training_name,
        )
        DaysOfWeekChooser()
        Row {
            TimePicker()
            TimePicker()
        }
    }






}