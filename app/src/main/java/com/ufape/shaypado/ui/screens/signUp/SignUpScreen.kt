package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.ufape.shaypado.ui.components.RoundedButton
import com.ufape.shaypado.ui.theme.AtIcon
import com.ufape.shaypado.ui.theme.KeyIcon
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.Chip
import com.ufape.shaypado.ui.components.ChipSelectionGroup
import com.ufape.shaypado.ui.components.LabeledCheckbox
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.theme.ShaypadoImage
import com.ufape.shaypado.ui.theme.SmilingFaceIcon
import com.ufape.shaypado.ui.theme.UserOutlinedIcon

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel: SignUpViewModel = viewModel()
    val currentTabIndex by viewModel.currentTabIndex.collectAsState()

    LaunchedEffect(key1 = LocalContext.current){
        viewModel.registerEvent.collect{
            if (it.isSuccess){
                navController.navigate(MobileNavigationScreen.NavRoot.route){
                    popUpTo(AuthNavigationScreen.NavRoot.route){
                        inclusive = true
                    }
                }
            } else {
                //TODO: show error
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .padding(top = 102.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            ShaypadoImage()

            Spacer(modifier = Modifier.height(80.dp))

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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RoundedButton(onClick = {
                        when (currentTabIndex) {
                            0 -> navController.popBackStack()
                            else -> viewModel.onTabSelected(currentTabIndex - 1)
                        }
                    })
                    Spacer(modifier = Modifier.width(16.dp))
                    AppText(
                        textType = TextType.TITLE_MEDIUM,
                        text = when (currentTabIndex) {
                            0 -> R.string.sign_up_user_type_title
                            1 -> R.string.sign_up_title
                            2 -> R.string.sign_up_workout_title
                            3 -> R.string.sign_up_person_data_title
                            else -> R.string.label
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier =
                    Modifier
                        .fillMaxHeight(0.85f)
                        .verticalScroll(rememberScrollState())
                )
                {
                    when (currentTabIndex) {
                        0 -> UserType(viewModel = viewModel)
                        1 -> UserAccountData(viewModel = viewModel)
                        2 -> UserTrainingStyle()
                        3 -> UserMainData()
                    }
                }

                AppButton(
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    text = if (currentTabIndex == 3) {
                        R.string.sign_up_finish
                    } else {
                        R.string.button_next
                    },
                    onClick = {
                        when (currentTabIndex) {
                            0 -> {
                                viewModel.onUserTypeNextButtonClicked()
                            }

                            1 -> {
                                viewModel.onUserDataEvent(UserAccountFormEvent.OnSubmit)
                            }

                            2 -> {
                                viewModel.onWorkoutNextButtonClicked()
                            }

                            3 -> {
                                viewModel.onPhysicalEvaluationDataEvent(
                                    UserPhysicalEvaluationFormEvent.OnSubmit
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun UserType(viewModel: SignUpViewModel) {
    ChipSelectionGroup(
        errorMessage = viewModel.userAccountDataState.userTypeError,
        selectedChip = viewModel.userAccountDataState.userType,
        chips = listOf(
            Chip(
                value = "0",
                label = R.string.sign_up_find_professional_usertype
            ) { SmilingFaceIcon() },
            Chip(
                value = "1",
                label = R.string.sign_up_find_normal_usertype
            ) { UserOutlinedIcon() },
        ),
        chipsHeight = 100.dp,
        onChipSelected = {
            viewModel.onUserTypeChanged(it)
        },
    )
}


@Composable
fun UserAccountData(
    viewModel: SignUpViewModel = viewModel(),
) {
    CustomTextField(
        value = viewModel.userAccountDataState.name,
        errorMessage = viewModel.userAccountDataState.nameError,
        onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnNameChanged(it)) },
        leadingIcon = { UserOutlinedIcon() },
        placeholder = R.string.input_user_name
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userAccountDataState.email,
        errorMessage = viewModel.userAccountDataState.emailError,
        keyboardType = KeyboardType.Email,
        onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnEmailChanged(it)) },
        leadingIcon = { AtIcon() },
        placeholder = R.string.input_email
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
        leadingIcon = { AtIcon() },
        placeholder = R.string.input_email_confirmation
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userAccountDataState.password,
        errorMessage = viewModel.userAccountDataState.passwordError,
        keyboardType = KeyboardType.Password,
        onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnPasswordChanged(it)) },
        leadingIcon = { KeyIcon() },
        placeholder = R.string.input_password
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
        placeholder = R.string.input_password_confirmation
    )
}

@Composable
fun UserTrainingStyle(
    viewModel: SignUpViewModel = viewModel()
) {
    ChipSelectionGroup(
        errorMessage = viewModel.userAccountDataState.workoutTypeError,
        selectedChip = viewModel.userAccountDataState.workoutType,
        chips = listOf(
            Chip(
                value = "0",
                label = R.string.sign_up_saved_workout
            ) { SmilingFaceIcon() },
            Chip(
                value = "1",
                label = R.string.sign_up_yourself
            ) { SmilingFaceIcon() },
            Chip(
                value = "2",
                label = R.string.sign_up_find_professional
            ) { UserOutlinedIcon() },
        ),
        onChipSelected = {
            viewModel.onWorkoutTypeChanged(it)
        },
    )
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
        placeholder = R.string.input_height
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
        placeholder = R.string.input_weight
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
        placeholder = R.string.input_goal
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomTextField(
        value = viewModel.userPhysicalEvaluationDataState.workoutFrequency,
        errorMessage = viewModel.userPhysicalEvaluationDataState.workoutFrequencyError,
        keyboardType = KeyboardType.Number,
        onValueChange = {
            viewModel.onPhysicalEvaluationDataEvent(
                UserPhysicalEvaluationFormEvent.OnWorkoutFrequencyChanged(
                    it
                )
            )
        },
        placeholder = R.string.input_workout_per_week
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {
        AppText(text = R.string.input_any_disease, textType = TextType.LABEL_MEDIUM)
        LabeledCheckbox(
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
    }
}