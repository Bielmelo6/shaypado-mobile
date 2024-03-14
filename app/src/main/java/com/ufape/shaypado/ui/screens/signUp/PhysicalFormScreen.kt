package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.CameraButton
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage
import com.ufape.shaypado.ui.components.Camera
import com.ufape.shaypado.util.compressImage

@Composable
fun PhysicalFormScreen(
    navController: NavController,
    viewModel: SignUpViewModel,
    showSnackbar : (String) -> Unit,
) {
    val context = LocalContext.current

    var showCamera by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = viewModel.userRegisterEvent) {
        viewModel.userRegisterEvent.collect {
            if (it is Result.Success) {
                navController.navigate(AuthNavigationScreen.SignUserCreated.route)
            }else if (it is Result.Error) {
                showSnackbar(it.exception.getErrorMessage(context))
            }
        }
    }

    if (showCamera) {
        return Camera(
            onBackButton = {
                showCamera = false
            },
            onPicture = {
                val file = it.compressImage(context)
                viewModel.fetchBodyFat(file!!.absolutePath)
                showCamera = false
            },
            context = context
        )
    }

    AppHeader(navController = navController, title = R.string.sign_up_person_data_title)

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier =
        Modifier
            .fillMaxHeight(0.9f)
            .verticalScroll(rememberScrollState())
    )
    {
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
                CameraButton(
                    onClick = {
                        showCamera = true
                    }
                )
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

        HorizontalDivider()

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = viewModel.userAccountDataState.termsAccepted,
                onCheckedChange = {
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnTermsAcceptedChanged(
                            it
                        )
                    )
                })
            AppText(
                text = R.string.input_terms_acceptance, textType = TextType.TITLE_MEDIUM
            )

            TextButton(onClick = {

            }) {
                AppText(
                    color = MaterialTheme.colorScheme.primary,
                    text = R.string.input_terms_link,
                    textType = TextType.TITLE_MEDIUM
                )
            }

            AppText(
                text = R.string.input_terms_acceptance2, textType = TextType.TITLE_MEDIUM
            )
        }
        if (viewModel.userAccountDataState.termsAcceptedError != null && !viewModel.userAccountDataState.saveCorporalData) {
            AppText(
                textType = TextType.LABEL_MEDIUM,
                text = viewModel.userAccountDataState.termsAcceptedError!!,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

    AppButton(
        text =  R.string.sign_up_finish,
        onClick = {
            viewModel.onUserDataEvent(UserAccountFormEvent.OnSubmit)
        }
    )
}
