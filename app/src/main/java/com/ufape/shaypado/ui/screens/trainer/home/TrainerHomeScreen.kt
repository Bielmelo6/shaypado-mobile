package com.ufape.shaypado.ui.screens.trainer.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen

@Composable
fun TrainerHomeScreen(
    navController: NavController
) {
    ClassDetailsRenderItem{
        navController.navigate(
            TrainerNavigationScreen.ClassDetails.route
        )
    }
    Spacer(modifier = Modifier.height(20.dp))

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items (10) {
            UserDetailsRenderItem(
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_student),
                        contentDescription = "Voltar",
                    )
                },
                onPress = {
                    navController.navigate(
                        TrainerNavigationScreen.StudentDetails.route
                    )
                }
            )
        }
    }
}

@Composable
@Preview
fun TrainingDetailsRenderItem(
    title: String = "Nome",
    description: String = "Descrição",
    leadingButtonDisabled: Boolean = false,
    trailingButtonDisabled: Boolean = false,
    onTrailingButtonPressed: () -> Unit = { },
    onLeadingButtonPressed: () -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        AppText(
            fillWidth = true,
            textType = TextType.TITLE_MEDIUM,
            text = title,
        )

        AppText(
            fillWidth = true,
            textType = TextType.TITLE_SMALL,
            text = description,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledIconButton(
                shape = RoundedCornerShape(8.dp),
                enabled = !leadingButtonDisabled,
                onClick = onLeadingButtonPressed,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "Voltar",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            FilledIconButton(
                shape = RoundedCornerShape(8.dp),
                enabled = !trailingButtonDisabled,
                onClick = onTrailingButtonPressed,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Voltar",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

@Composable
@Preview
fun ClassDetailsRenderItem(
    name: String = "Nome",
    description: String = "Descrição",
    students: Int = 0,
    day: String = "S",
    onPress: (() -> Unit)? = null
) {
    val modifier = if (onPress != null) Modifier.clickable { onPress() } else Modifier
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            AppText(
                fillWidth = true,
                textType = TextType.TITLE_MEDIUM,
                text = name,
            )

            AppText(
                fillWidth = true,
                textType = TextType.TITLE_SMALL,
                text = description,
            )

            AppText(
                fillWidth = true,
                textType = TextType.TITLE_SMALL,
                text = "$students alunos",
            )
        }
        Column(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(4.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                textType = TextType.HEADLINE_SMALL,
                text = day,
            )
        }

    }
}

@Composable
fun UserDetailsRenderItem(
    leadingIcon: @Composable () -> Unit = { },
    trailingIcon: @Composable () -> Unit = { },
    name: String = "Nome",
    description: String = "Descrição",
    onPress : () -> Unit = { }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onPress() }
            .padding(
                start = 16.dp,
                top = 14.dp,
                bottom = 14.dp,
                end = 16.dp
            )
    ) {
        leadingIcon()
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            AppText(
                fillWidth = true,
                textType = TextType.TITLE_MEDIUM,
                text = name,
            )

            AppText(
                fillWidth = true,
                textType = TextType.TITLE_SMALL,
                text = description,
            )
        }
        trailingIcon()
    }

}

@Composable
fun Dropdown(
    isExpanded: Boolean = false,
    @StringRes title: Int = R.string.label,
    toggle: () -> Unit = { },
    endHeaderContent: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { },
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable { toggle() },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    textType = TextType.TITLE_LARGE,
                    text = title,
                )
                FilledIconButton(
                    shape = RoundedCornerShape(8.dp),
                    onClick = { toggle() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (isExpanded) "Expandir" else "Recolher",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }


            }

            endHeaderContent()

        }

        if (isExpanded) {
            content()
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider()
    }


}

@Composable
@Preview
fun DropdownPreview() {
    var dropdownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
    ) {
        Dropdown(
            isExpanded = dropdownExpanded,
            toggle = { dropdownExpanded = !dropdownExpanded },
            endHeaderContent = {
                AddButton()
            }
        ) {
            for (i in 0..2) {
                Spacer(modifier = Modifier.width(8.dp))
                UserDetailsRenderItem(
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_student),
                            contentDescription = "Voltar",
                        )
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun ClassDetailsRenderItemPreview() {
    UserDetailsRenderItem(
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_student),
                contentDescription = "Voltar",
            )
        }
    )
}

@Composable
@Preview
fun ExerciseDetailsRenderItemPreview() {
    UserDetailsRenderItem(
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_training),
                contentDescription = "Voltar",
            )
        },
        trailingIcon = {
            FilledIconButton(
                shape = RoundedCornerShape(8.dp),
                onClick = { },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Expandir"
                )
            }
        }
    )
}