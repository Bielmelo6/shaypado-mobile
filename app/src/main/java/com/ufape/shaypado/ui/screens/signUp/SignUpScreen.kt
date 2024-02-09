package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.theme.KeyIcon
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.Chip
import com.ufape.shaypado.ui.components.ChipSelectionGroup
import com.ufape.shaypado.ui.components.GroupedLabeledCheckbox
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.theme.EmailIcon
import com.ufape.shaypado.ui.theme.PersonIcon
import com.ufape.shaypado.util.Result

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val currentTabIndex by viewModel.currentTabIndex.collectAsState()

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.registerEvent.collect {
            if (it is Result.Success) {
                navController.navigate(MobileNavigationScreen.NavRoot.route) {
                    popUpTo(AuthNavigationScreen.NavRoot.route) {
                        inclusive = true
                    }
                }
            } else {
                //TODO: show error
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(36.dp))

        AppText(
            textType = TextType.HEADLINE_MEDIUM,
            textAlignment = TextAlign.Center,
            text = when (currentTabIndex) {
                0 -> R.string.sign_up_title
                1 -> R.string.sign_up_user_type_title
                2 -> R.string.sign_up_person_data_title
                3 -> R.string.sign_up_workout_title
                else -> R.string.label
            }
        )

        Spacer(modifier = Modifier.height(56.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
                .padding(20.dp)
        )
        {
            Column(
                modifier =
                Modifier
                    .fillMaxHeight(0.85f)
                    .verticalScroll(rememberScrollState())
            )
            {
                when (currentTabIndex) {
                    0 -> UserAccountData(viewModel = viewModel)
                    1 -> UserType(viewModel = viewModel)
                    2 -> UserMainData()
                    3 -> UserTrainingStyle()
                }
            }

            AppButton(
                text = if (currentTabIndex == 3) {
                    R.string.sign_up_finish
                } else {
                    R.string.button_next
                },
                onClick = {
                    when (currentTabIndex) {
                        0 -> {
                            viewModel.onUserDataEvent(UserAccountFormEvent.OnSubmit)
                        }

                        1 -> {
                            viewModel.onUserTypeNextButtonClicked()
                        }

                        2 -> {
                            viewModel.onPhysicalEvaluationDataEvent(
                                UserPhysicalEvaluationFormEvent.OnSubmit
                            )
                        }

                        3 -> {
                            viewModel.onWorkoutNextButtonClicked()
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun UserType(viewModel: SignUpViewModel) {

    val chips = listOf(
        Chip(
            value = "0",
            icon = R.drawable.personal_chip,
            label = R.string.sign_up_find_professional_usertype
        ),
        Chip(
            value = "1",
            icon = R.drawable.common_chip,
            label = R.string.sign_up_find_normal_usertype
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChipSelectionGroup(
            errorMessage = viewModel.userAccountDataState.userTypeError,
            selectedChip = viewModel.userAccountDataState.userType,
            chips = chips,
            onChipSelected = {
                viewModel.onUserTypeChanged(it)
            },
        )
    }
}


@Composable
fun UserAccountData(
    viewModel: SignUpViewModel = viewModel(),
) {
    CustomTextField(
        value = viewModel.userAccountDataState.name,
        errorMessage = viewModel.userAccountDataState.nameError,
        onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnNameChanged(it)) },
        leadingIcon = { PersonIcon() },
        label = R.string.input_user_name,
        placeholder = R.string.input_user_name_placeholder
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userAccountDataState.email,
        errorMessage = viewModel.userAccountDataState.emailError,
        keyboardType = KeyboardType.Email,
        onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnEmailChanged(it)) },
        leadingIcon = { EmailIcon() },
        placeholder = R.string.input_email_placeholder,
        label = R.string.input_email
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userAccountDataState.emailConfirmation,
        errorMessage = viewModel.userAccountDataState.emailConfirmationError,
        keyboardType = KeyboardType.Email,
        onValueChange = {
            viewModel.onUserDataEvent(
                UserAccountFormEvent.OnEmailConfirmationChanged(
                    it
                )
            )
        },
        leadingIcon = { EmailIcon() },
        placeholder = R.string.input_email_confirmation_placeholder,
        label = R.string.input_email_confirmation
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userAccountDataState.password,
        errorMessage = viewModel.userAccountDataState.passwordError,
        keyboardType = KeyboardType.Password,
        onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnPasswordChanged(it)) },
        leadingIcon = { KeyIcon() },
        placeholder = R.string.input_password_placeholder,
        label = R.string.input_password
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userAccountDataState.passwordConfirmation,
        errorMessage = viewModel.userAccountDataState.passwordConfirmationError,
        onValueChange = {
            viewModel.onUserDataEvent(
                UserAccountFormEvent.OnPasswordConfirmationChanged(
                    it
                )
            )
        },
        keyboardType = KeyboardType.Password,
        leadingIcon = { KeyIcon() },
        placeholder = R.string.input_password_confirmation_placeholder,
        label = R.string.input_password_confirmation
    )
}

@Composable
fun UserTrainingStyle(
    viewModel: SignUpViewModel = viewModel()
) {
    val chips = listOf(
        Chip(
            value = "0",
            icon = R.drawable.common_chip,
            label = R.string.sign_up_yourself
        ),
        Chip(
            value = "1",
            icon = R.drawable.common_chip,
            label = R.string.sign_up_saved_workout
        ),
        Chip(
            value = "2",
            icon = R.drawable.common_chip,
            label = R.string.sign_up_find_professional
        )

    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChipSelectionGroup(
            errorMessage = viewModel.userAccountDataState.workoutTypeError,
            selectedChip = viewModel.userAccountDataState.workoutType,
            chips = chips,
            onChipSelected = {
                viewModel.onWorkoutTypeChanged(it)
            },
        )
    }
}

@Composable
fun UserMainData(
    viewModel: SignUpViewModel = viewModel()
) {
    CustomTextField(
        value = viewModel.userPhysicalEvaluationDataState.height,
        errorMessage = viewModel.userPhysicalEvaluationDataState.heightError,
        keyboardType = KeyboardType.Number,
        onValueChange = {
            viewModel.onPhysicalEvaluationDataEvent(
                UserPhysicalEvaluationFormEvent.OnHeightChanged(
                    it
                )
            )
        },
        placeholder = R.string.input_height_placeholder,
        label = R.string.input_height
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userPhysicalEvaluationDataState.weight,
        errorMessage = viewModel.userPhysicalEvaluationDataState.weightError,
        keyboardType = KeyboardType.Number,
        onValueChange = {
            viewModel.onPhysicalEvaluationDataEvent(
                UserPhysicalEvaluationFormEvent.OnWeightChanged(
                    it
                )
            )
        },
        placeholder = R.string.input_weight_placeholder,
        label = R.string.input_weight
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userPhysicalEvaluationDataState.objective,
        errorMessage = viewModel.userPhysicalEvaluationDataState.objectiveError,
        onValueChange = {
            viewModel.onPhysicalEvaluationDataEvent(
                UserPhysicalEvaluationFormEvent.OnObjectiveChanged(
                    it
                )
            )
        },
        placeholder = R.string.input_goal_placeholder,
        label = R.string.input_goal
    )

    Spacer(modifier = Modifier.height(16.dp))

    AppText(text = R.string.input_any_disease, textType =  TextType.TITLE_MEDIUM)
    GroupedLabeledCheckbox(
        isChecked = viewModel.userPhysicalEvaluationDataState.anyDisease,
        checkedLabel = R.string.yes,
        unCheckedLabel = R.string.no,
        onCheckedChange = {
            viewModel.onPhysicalEvaluationDataEvent(
                UserPhysicalEvaluationFormEvent.OnAnyDiseaseChanged(
                    it
                )
            )
    })

    AppText(text = R.string.input_register_corporal_data, textType = TextType.TITLE_MEDIUM)
    GroupedLabeledCheckbox(
        isChecked = viewModel.userPhysicalEvaluationDataState.saveCorporalData,
        checkedLabel = R.string.yes,
        unCheckedLabel = R.string.no,
        onCheckedChange = {
            viewModel.onPhysicalEvaluationDataEvent(
                UserPhysicalEvaluationFormEvent.OnCorporalDataChanged(
                    it
                )
            )
    })
}