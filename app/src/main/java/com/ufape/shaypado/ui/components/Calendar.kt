package com.ufape.shaypado.ui.components

import android.provider.CalendarContract.Colors
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ufape.shaypado.R

@Composable
@Preview
fun DaysOfWeekChooserItem(
    isSelected: Boolean = false,
    onSelected: () -> Unit = {},
    @StringRes label: Int = R.string.letter
) {
    val colors = if (isSelected) {
        IconButtonDefaults.filledIconButtonColors()
    } else {
        IconButtonDefaults.filledIconButtonColors(
            containerColor = Color.Transparent,
        )
    }

    FilledIconButton(
        colors = colors,
        onClick = onSelected,
    ) {
        AppText(
            textAlignment = TextAlign.Center,
            textType = TextType.TITLE_MEDIUM,
            text = label
        )
    }
}

@Composable
@Preview
fun DaysOfWeekChooser(
    onItemSelected: (List<Boolean>) -> Unit = {},
    itemsSelected: List<Boolean> = listOf(
        false,
        false,
        false,
        false,
        false,
        false,
        false
    )
) {
    var selectedList by rememberSaveable { mutableStateOf(itemsSelected) }

    fun handleOnSelected(index: Int) {
        val newList = selectedList.toMutableList()
        newList[index] = !newList[index]
        selectedList = newList
        onItemSelected(selectedList)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,

        ) {
        DaysOfWeekChooserItem(
            isSelected = selectedList[0],
            onSelected = { handleOnSelected(0) },
            label = R.string.sunday_short
        )
        DaysOfWeekChooserItem(
            isSelected = selectedList[1],
            onSelected = { handleOnSelected(1) },
            label = R.string.monday_short
        )
        DaysOfWeekChooserItem(
            isSelected = selectedList[2],
            onSelected = { handleOnSelected(2) },
            label = R.string.tuesday_short
        )
        DaysOfWeekChooserItem(
            isSelected = selectedList[3],
            onSelected = { handleOnSelected(3) },
            label = R.string.wednesday_short
        )
        DaysOfWeekChooserItem(
            isSelected = selectedList[4],
            onSelected = { handleOnSelected(4) },
            label = R.string.thursday_short
        )
        DaysOfWeekChooserItem(
            isSelected = selectedList[5],
            onSelected = { handleOnSelected(5) },
            label = R.string.friday_short
        )
        DaysOfWeekChooserItem(
            isSelected = selectedList[6],
            onSelected = { handleOnSelected(6) },
            label = R.string.saturday_short
        )

    }
}

@Composable
@Preview
fun TimePicker(
    title: String = "Select Time",
    selectedHour : Int = 0,
    selectedMinute : Int = 0,
    onConfirm: (state: TimePickerState) -> Unit = {},
) {
    var showPicker by rememberSaveable { mutableStateOf(false) }
    val state = rememberTimePickerState(
        is24Hour = true,
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    TextButton(onClick = {
        showPicker = true
    }) {
        Text(state.hour.toString() + ":" + state.minute.toString())
    }

    if (!showPicker) return

    Dialog(
        onDismissRequest = {showPicker = false},
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                TimeInput(state = state)
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = {showPicker = false}
                    ) { Text("Cancel") }
                    TextButton(
                        onClick = {
                            onConfirm(state)
                            showPicker = false
                        }
                    ) { Text("OK") }
                }
            }
        }
    }
}