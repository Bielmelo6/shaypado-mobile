package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.GroupedLabeledCheckbox
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.util.Result

@Composable
fun ProfileFormScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {

    LaunchedEffect(key1 = viewModel.validationStatus) {
        viewModel.validationStatus.collect {
            if (it is Result.Success) {
                if (viewModel.userAccountDataState.saveCorporalData) {
                    navController.navigate(MobileNavigationScreen.NavRoot.route)
                    viewModel.resetValidationStatus()
                }
            }
        }
    }

    LaunchedEffect(key1 = viewModel.registerEvent) {
        viewModel.registerEvent.collect {
            if (it is Result.Success) {
                if (viewModel.userAccountDataState.saveCorporalData) {
                    navController.navigate(AuthNavigationScreen.SignUpPhysicalForm.route)
                }
            }
        }
    }

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
            value = viewModel.userAccountDataState.weight,
            errorMessage = viewModel.userAccountDataState.weightError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserAccountFormEvent.OnWeightChanged(
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
            value = viewModel.userAccountDataState.height,
            errorMessage = viewModel.userAccountDataState.heightError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserAccountFormEvent.OnHeightChanged(
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
            value = viewModel.userAccountDataState.objective,
            errorMessage = viewModel.userAccountDataState.objectiveError,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserAccountFormEvent.OnObjectiveChanged(
                        it
                    )
                )
            },
            placeholder = R.string.input_goal_placeholder,
            label = R.string.input_goal
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppText(text = R.string.input_any_disease, textType = TextType.TITLE_MEDIUM)
        GroupedLabeledCheckbox(
            isChecked = viewModel.userAccountDataState.anyDisease,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                viewModel.onUserDataEvent(
                    UserAccountFormEvent.OnAnyDiseaseChanged(
                        it
                    )
                )
            })

        AppText(text = R.string.input_register_corporal_data, textType = TextType.TITLE_MEDIUM)
        GroupedLabeledCheckbox(
            isChecked = viewModel.userAccountDataState.saveCorporalData,
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
            Divider()

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = viewModel.userAccountDataState.termsAccepted, onCheckedChange = {
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnTermsAcceptedChanged(
                            it
                        )
                    )
                })
                AppText(
                    text = R.string.input_terms_acceptance,
                    textType = TextType.TITLE_MEDIUM
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
                    text = R.string.input_terms_acceptance2,
                    textType = TextType.TITLE_MEDIUM
                )
            }
        }
    }
}