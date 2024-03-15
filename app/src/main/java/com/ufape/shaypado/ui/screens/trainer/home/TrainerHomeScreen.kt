package com.ufape.shaypado.ui.screens.trainer.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AddButton
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.TrainerNavigationScreen
import com.ufape.shaypado.ui.screens.shimmers.ErrorScreen
import com.ufape.shaypado.ui.screens.shimmers.TrainerHomeShimmer
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun TrainerHomeScreen(
    navController: NavController,
    showSnackBar: (String) -> Unit
) {
    val viewModel = hiltViewModel<TrainerHomeViewModel>()
    val context = LocalContext.current

    var status by remember { mutableStateOf<Result<Unit>>(Result.Loading) }

    LaunchedEffect(Unit) {
        viewModel.fetchClasses()
    }

    LaunchedEffect(key1 = viewModel.classesData) {
        viewModel.classesData.collect {
            status = when (it) {
                is Result.Error -> {
                    showSnackBar(it.exception.getErrorMessage(context))
                    it
                }

                else -> it
            }
        }
    }

    if (status is Result.Loading) {
        return TrainerHomeShimmer()
    }

    if (status is Result.Error) {
        return ErrorScreen {
            viewModel.fetchClasses()
        }
    }

    if (viewModel.classes.isEmpty()) {
        TrainerHomeScreenEmptyList(navController)
        return
    }

    val lazyListState = rememberLazyListState()


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        AppText(
            textType = TextType.HEADLINE_MEDIUM,
            textAlignment = TextAlign.Center,
            text = R.string.home,
        )


        AddButton(onClick = {
            navController.navigate(TrainerNavigationScreen.CreateClasses.route)
        })
    }

    Spacer(modifier = Modifier.height(16.dp))


    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.classes.size) {
            ClassDetailsRenderItem(
                name = viewModel.classes[it].name,
                description = viewModel.classes[it].startTime + " - " + viewModel.classes[it].endTime,
                students = viewModel.classes[it].students.size,
                day = "S",
                onPress = {
                    navController.navigate(
                        TrainerNavigationScreen.ClassDetails.shortName + "/${viewModel.classes[it].id}"
                    )
                }
            )
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    val visibleClassIndex by remember(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .filter { it != -1 } // Evitar -1, que indica nenhum item visível
            .distinctUntilChanged() // Para evitar emissões repetidas do mesmo índice
    }.collectAsState(initial = 0)

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(viewModel.classes[visibleClassIndex].students.size) {
            UserDetailsRenderItem(
                name = viewModel.classes[visibleClassIndex].students[it].name,
                description = "",
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_student),
                        contentDescription = "Voltar",
                    )
                },
                onPress = {
                    navController.navigate(
                        TrainerNavigationScreen.StudentDetails.shortName + "/${viewModel.classes[visibleClassIndex].students[it].friendshipCode}"
                    )
                }
            )
        }
    }
}

@Composable
fun TrainerHomeScreenEmptyList(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.shaypado_home),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        AppText(
            text = R.string.no_classes,
            textType = TextType.TITLE_MEDIUM,
            fillWidth = true,
            textAlignment = TextAlign.Center
        )
    }

    AppButton(
        variant = ButtonVariant.TERTIARY,
        text = R.string.register_first_class,
        onClick = {
            navController.navigate(
                TrainerNavigationScreen.CreateClasses.route
            )
        },
    )
}

@Preview
@Composable
fun TrainerHomeScreenEmptyPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TrainerHomeScreenEmptyList(
            rememberNavController()
        )
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
    trailingIcon: @Composable () -> Unit = { },
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
        Row {
            Column (
                modifier = Modifier
                    .weight(1f),
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
            }
            trailingIcon()
        }
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
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer
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
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer                )
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
    fillWidth: Boolean = false,
    onPress: (() -> Unit)? = null
) {
    var modifier = if (onPress != null) Modifier.clickable { onPress() } else Modifier
    modifier = if (fillWidth) modifier.fillMaxWidth() else modifier.width(350.dp)
    Row(
        modifier = modifier
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
    name: String = "",
    description: String = "",
    onPress: () -> Unit = { }
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