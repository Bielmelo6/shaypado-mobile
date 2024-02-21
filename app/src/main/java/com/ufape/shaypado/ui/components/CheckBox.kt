package com.ufape.shaypado.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.ufape.shaypado.R

@Composable
@Preview
fun GroupedLabeledCheckbox(
    title : Int = R.string.label,
    @StringRes checkedLabel: Int = R.string.label,
    @StringRes unCheckedLabel: Int = R.string.label,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    @StringRes errorMessage: Int? = null
) {
    AppText(text = title, textType = TextType.TITLE_MEDIUM)

    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Checkbox(checked = isChecked, onCheckedChange = {
            onCheckedChange(it)
        })
        AppText(
            text = checkedLabel,
            textType = TextType.LABEL_MEDIUM
        )

        Checkbox(checked = !isChecked, onCheckedChange = {
            onCheckedChange(!it)
        })

        AppText(
            text = unCheckedLabel,
            textType = TextType.LABEL_MEDIUM
        )
    }

    if (errorMessage != null) {
        AppText(
            textType = TextType.LABEL_MEDIUM,
            text = errorMessage,
            color = MaterialTheme.colorScheme.error
        )
    }
}