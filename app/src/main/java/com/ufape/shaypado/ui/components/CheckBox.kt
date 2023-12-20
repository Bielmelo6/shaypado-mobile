package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.ufape.shaypado.R

@Composable
fun LabeledCheckbox(
    @StringRes checkedLabel: Int = R.string.label,
    @StringRes unCheckedLabel: Int = R.string.label,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Checkbox(checked = isChecked, onCheckedChange = {
            onCheckedChange(it)
        })
        AppText(
            text = if (isChecked) checkedLabel else unCheckedLabel,
            textType = TextType.LABEL_MEDIUM
        )
    }
}