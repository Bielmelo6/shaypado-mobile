package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
        IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
        )
    } else {
        IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    }

    FilledIconButton(
        modifier = Modifier
            .width(43.dp)
            .height(56.dp),
        colors = colors,
        onClick = onSelected,
        shape = RoundedCornerShape(8.dp)
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

    fun handleOnSelected(index: Int) {
        val newList = itemsSelected.toMutableList()
        newList[index] = !newList[index]
        onItemSelected(newList)
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    )
    {

        AppText(
            textType = TextType.TITLE_MEDIUM,
            text = "Dias da semana",
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            DaysOfWeekChooserItem(
                isSelected = itemsSelected[0],
                onSelected = { handleOnSelected(0) },
                label = R.string.sunday_short
            )
            DaysOfWeekChooserItem(
                isSelected = itemsSelected[1],
                onSelected = { handleOnSelected(1) },
                label = R.string.monday_short
            )
            DaysOfWeekChooserItem(
                isSelected = itemsSelected[2],
                onSelected = { handleOnSelected(2) },
                label = R.string.tuesday_short
            )
            DaysOfWeekChooserItem(
                isSelected = itemsSelected[3],
                onSelected = { handleOnSelected(3) },
                label = R.string.wednesday_short
            )
            DaysOfWeekChooserItem(
                isSelected = itemsSelected[4],
                onSelected = { handleOnSelected(4) },
                label = R.string.thursday_short
            )
            DaysOfWeekChooserItem(
                isSelected = itemsSelected[5],
                onSelected = { handleOnSelected(5) },
                label = R.string.friday_short
            )
            DaysOfWeekChooserItem(
                isSelected = itemsSelected[6],
                onSelected = { handleOnSelected(6) },
                label = R.string.saturday_short
            )

        }
    }
}

@Composable
@Preview
fun TimePicker(
    title: String = "Select Time",
    label: Int = R.string.label,
    time : String = "00:00",
    onConfirm: (state: String) -> Unit = {},
) {
    var selectedHour = 0
    var selectedMinute = 0

    try {
        selectedHour = time.substringBefore(":").toInt()
        selectedMinute = time.substringAfter(":").toInt()
    } catch (_: Exception){
    }

    var showPicker by rememberSaveable { mutableStateOf(false) }
    val state = rememberTimePickerState(
        is24Hour = true,
        initialHour = selectedHour,
        initialMinute = selectedMinute
    )

    Column(modifier = Modifier.fillMaxWidth())
    {

        AppText(
            textType = TextType.TITLE_MEDIUM,
            text = label,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { showPicker = true }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            AppText(
                textType = TextType.HEADLINE_SMALL,
                text = String.format("%02d", selectedHour) + ":" + String.format("%02d", selectedMinute)
            )
            Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = "Select Time",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

    }



    if (!showPicker) return

    Dialog(
        onDismissRequest = { showPicker = false },
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
                AppText(
                    fillWidth = true,
                    text = title,
                    textType = TextType.TITLE_MEDIUM
                )
                Spacer(modifier = Modifier.height(8.dp))
                TimeInput(state = state)
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    TextButton(
                        onClick = { showPicker = false }
                    ) {
                        AppText(
                            text = R.string.cancel,
                            textType = TextType.TITLE_MEDIUM
                        )
                    }
                    TextButton(
                        onClick = {
                            onConfirm(
                                "${state.hour}:${state.minute}"
                            )
                            showPicker = false
                        }
                    ) {
                        AppText(
                            text = R.string.confirm,
                            textType = TextType.TITLE_MEDIUM
                        )
                    }
                }
            }
        }
    }
}