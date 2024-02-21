package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.ufape.shaypado.ui.components.AppSnackBar
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.GroupedLabeledCheckbox
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun ProfileFormScreen(
    navController: NavController, viewModel: SignUpViewModel
) {
    var snackbarMessage: String? by remember { mutableStateOf(null) }
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.validationStatus) {
        viewModel.validationStatus.collect {
            if (it is Result.Success) {
                if (viewModel.userAccountDataState.saveCorporalData) {
                    navController.navigate(AuthNavigationScreen.SignUpPhysicalForm.route)
                    viewModel.resetValidationStatus()
                }
            }
        }
    }

    LaunchedEffect(key1 = viewModel.userRegisterEvent) {
        viewModel.userRegisterEvent.collect {
            if (it is Result.Success) {
                navController.navigate(AuthNavigationScreen.SignUserCreated.route)
            } else if (it is Result.Error) {
                snackbarMessage = it.exception.getErrorMessage(context)
            }
        }
    }

    AppSnackBar(message = snackbarMessage, reset = { snackbarMessage = null }) {
        SignUpScreenBase(
            title = R.string.sign_up_person_data_title,
            buttonText = if (viewModel.userAccountDataState.saveCorporalData) R.string.button_next else R.string.sign_up_finish,
            onButtonClicked = {
                if (viewModel.userAccountDataState.saveCorporalData) {
                    viewModel.onUserDataEvent(UserAccountFormEvent.ValidateProfileData)
                } else {
                    viewModel.onUserDataEvent(UserAccountFormEvent.OnSubmit)
                }
            },
            navController = navController
        ) {
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
                trailingIcon = {
                    AppText(
                        color = MaterialTheme.colorScheme.primary,
                        text = R.string.input_objective_kg,
                        textType = TextType.LABEL_LARGE
                    )
                },
                placeholder = R.string.input_weight_placeholder,
                label = R.string.input_weight
            )

            Spacer(modifier = Modifier.height(16.dp))

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
                trailingIcon = {
                    AppText(
                        color = MaterialTheme.colorScheme.primary,
                        text = R.string.input_height_cm,
                        textType = TextType.LABEL_LARGE
                    )
                },
                placeholder = R.string.input_height_placeholder,
                label = R.string.input_height
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

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.healthIssue,
                errorMessage = viewModel.userPhysicalEvaluationDataState.healthIssueError,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnHealthIssueChanged(
                            it
                        )
                    )
                },
                placeholder = R.string.input_any_disease_placeholder,
                label = R.string.input_any_disease
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppText(text = R.string.input_register_corporal_data, textType = TextType.TITLE_MEDIUM)
            GroupedLabeledCheckbox(isChecked = viewModel.userAccountDataState.saveCorporalData,
                checkedLabel = R.string.yes,
                unCheckedLabel = R.string.no,
                onCheckedChange = {
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnCorporalDataChanged(
                            it
                        )
                    )
                })

            if (!viewModel.userAccountDataState.saveCorporalData) {
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
        }
    }
}