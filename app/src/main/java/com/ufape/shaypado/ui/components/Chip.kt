package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Chip(
    val value: String,
    @StringRes val label: Int,
    val leadingIcon: @Composable (() -> Unit)? = null
)

@Composable
fun ChipSelectionGroup(
    selectedChip: String? = null,
    onChipSelected: (String) -> Unit,
    chips: List<Chip>,
    chipsHeight: Dp = 55.dp,
) {
    var selected by rememberSaveable { mutableStateOf(selectedChip) }

    chips.forEachIndexed { _, chip ->
        val label = chip.label
        val value = chip.value
        val leadingIcon = chip.leadingIcon
        FilterChip(
            modifier = Modifier
                .height(chipsHeight)
                .fillMaxWidth(),
            border = null,
            onClick = {
                onChipSelected(value)
                selected = value
            },
            shape = RoundedCornerShape(15.dp),
            colors = FilterChipDefaults.filterChipColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            label = {
                AppText(textType = TextType.TITLE_MEDIUM, text = label)
            },
            selected = selected == value,
            leadingIcon = {
                leadingIcon?.invoke()
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}