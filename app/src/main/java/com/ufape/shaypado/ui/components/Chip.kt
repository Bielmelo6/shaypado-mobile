package com.ufape.shaypado.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class Chip(
    val value: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val informationTitle : String? = null,
    val informationText : String? = null,
    val onClick: () -> Unit = {}
)



@Composable
fun ChipSelectionGroup(
    selectedChip: String? = null,
    onChipSelected: (String) -> Unit,
    chips: List<Chip>,
    @StringRes errorMessage : Int? = null
) {
    var selected by rememberSaveable { mutableStateOf(selectedChip) }

    chips.forEachIndexed { _, chip ->
        val label = chip.label
        val value = chip.value
        val icon = chip.icon
        Column(
            modifier = Modifier
                .width(203.dp)
                .height(224.dp)
                .shadow(
                    elevation = 6.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .background(
                    if (selected == value) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(size = 8.dp)
                )
                .clickable(onClick = {
                    onChipSelected(value)
                    selected = value
                    chip.onClick()
                })
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row {
                if (chip.informationTitle != null && chip.informationText != null) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(28.dp)
                    )
                }

                Image(painter = painterResource(id = icon), contentDescription = "Icon", modifier = Modifier.size(100.dp))

                if (chip.informationTitle != null && chip.informationText != null) {
                    Tooltip(text = chip.informationTitle, title = chip.informationText)
                }
            }

            Spacer(modifier = Modifier.weight(1.0f))

            AppText(
                textType = TextType.HEADLINE_MEDIUM,
                text = label,
                textAlignment = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
    if (errorMessage != null) {
        AppText(textType = TextType.LABEL_MEDIUM, text = errorMessage)
    }
}