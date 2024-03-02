package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.CameraButton
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.util.Result

@Composable
fun PhysicalFormScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {

    LaunchedEffect(key1 = viewModel.validationStatus) {
        viewModel.validationStatus.collect {
            if (it is Result.Success) {
                navController.navigate(AuthNavigationScreen.SignUpPhysicalForm.route)
                viewModel.resetValidationStatus()
            }
        }
    }

    SignUpScreenBase(
        title = R.string.sign_up_person_data_title,
        buttonText = if (viewModel.userAccountDataState.saveCorporalData) R.string.button_next else R.string.sign_up_finish,
        onButtonClicked = {
            viewModel.onUserDataEvent(UserAccountFormEvent.OnSubmit)
        },
        navController = navController,
        topTitleSpacing = 24
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        )
        {
            Row (
                modifier = Modifier.weight(1f)
            ) {
                CustomTextField(
                    value = viewModel.userPhysicalEvaluationDataState.fatPercentage,
                    errorMessage = viewModel.userPhysicalEvaluationDataState.fatPercentageError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onPhysicalEvaluationDataEvent(
                            UserPhysicalEvaluationFormEvent.OnFatPercentageChanged(
                                it
                            )
                        )
                    },
                    trailingIcon = {
                        AppText(
                            color = MaterialTheme.colorScheme.primary,
                            text = R.string.input_percentage,
                            textType = TextType.LABEL_LARGE
                        )
                    },
                    placeholder = R.string.input_fat_percentage_placeholder,
                    label = R.string.input_fat_percentage
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row{
                CameraButton()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userPhysicalEvaluationDataState.armCircumference,
            errorMessage = viewModel.userPhysicalEvaluationDataState.armCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onPhysicalEvaluationDataEvent(
                    UserPhysicalEvaluationFormEvent.OnArmCircumferenceChanged(
                        it
                    )
                )
            },
            trailingIcon = {
                AppText(
                    color = MaterialTheme.colorScheme.primary,
                    text = R.string.input_height_cm,
                    textType = TextType.LABEL_LARGE
                )
            },
            placeholder = R.string.input_arm_circumference_placeholder,
            label = R.string.input_arm_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userPhysicalEvaluationDataState.waistCircumference,
            errorMessage = viewModel.userPhysicalEvaluationDataState.waistCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onPhysicalEvaluationDataEvent(
                    UserPhysicalEvaluationFormEvent.OnWaistCircumferenceChanged(
                        it
                    )
                )
            },
            trailingIcon = {
                AppText(
                    color = MaterialTheme.colorScheme.primary,
                    text = R.string.input_height_cm,
                    textType = TextType.LABEL_LARGE
                )
            },
            placeholder = R.string.input_waist_circumference_placeholder,
            label = R.string.input_waist_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userPhysicalEvaluationDataState.abdomenCircumference,
            errorMessage = viewModel.userPhysicalEvaluationDataState.abdomenCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onPhysicalEvaluationDataEvent(
                    UserPhysicalEvaluationFormEvent.OnAbdomenCircumferenceChanged(
                        it
                    )
                )
            },
            trailingIcon = {
                AppText(
                    color = MaterialTheme.colorScheme.primary,
                    text = R.string.input_height_cm,
                    textType = TextType.LABEL_LARGE
                )
            },
            placeholder = R.string.input_abdomen_circumference_placeholder,
            label = R.string.input_abdomen_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userPhysicalEvaluationDataState.hipCircumference,
            errorMessage = viewModel.userPhysicalEvaluationDataState.hipCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onPhysicalEvaluationDataEvent(
                    UserPhysicalEvaluationFormEvent.OnHipCircumferenceChanged(
                        it
                    )
                )
            },
            trailingIcon = {
                AppText(
                    color = MaterialTheme.colorScheme.primary,
                    text = R.string.input_height_cm,
                    textType = TextType.LABEL_LARGE
                )
            },
            placeholder = R.string.input_hip_circumference_placeholder,
            label = R.string.input_hip_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.thighCircumference,
        errorMessage = viewModel.userPhysicalEvaluationDataState.thighCircumferenceError,
        keyboardType = KeyboardType.Number,
        onValueChange = {
            viewModel.onPhysicalEvaluationDataEvent(
                UserPhysicalEvaluationFormEvent.OnThighCircumferenceChanged(
                    it
                )
            )
        },
        trailingIcon = {
            AppText(
                color = MaterialTheme.colorScheme.primary,
                text = R.string.input_height_cm,
                textType = TextType.LABEL_LARGE
            )
        },
        placeholder = R.string.input_thigh_circumference_placeholder,
        label = R.string.input_thigh_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userPhysicalEvaluationDataState.legCircumference,
            errorMessage = viewModel.userPhysicalEvaluationDataState.legCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onPhysicalEvaluationDataEvent(
                    UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged(
                        it
                    )
                )
            },
            trailingIcon = {
                AppText(
                    color = MaterialTheme.colorScheme.primary,
                    text = R.string.input_height_cm,
                    textType = TextType.LABEL_LARGE
                )
            },
            placeholder = R.string.input_leg_circumference_placeholder,
            label = R.string.input_leg_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
