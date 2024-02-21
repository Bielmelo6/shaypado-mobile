package com.ufape.shaypado.ui.screens.trainer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.GroupedLabeledCheckbox
import com.ufape.shaypado.ui.components.NextButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.screens.signUp.UserAccountFormEvent
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormEvent

@Composable
fun CmLabel() {
    AppText(
        color = MaterialTheme.colorScheme.primary,
        text = R.string.input_height_cm,
        textType = TextType.LABEL_LARGE
    )
}

@Composable
fun KgLabel() {
    AppText(
        color = MaterialTheme.colorScheme.primary,
        text = R.string.input_objective_kg,
        textType = TextType.LABEL_LARGE
    )
}

@Composable
fun MmLabel() {
    AppText(
        color = MaterialTheme.colorScheme.primary,
        text = R.string.input_mm,
        textType = TextType.LABEL_LARGE
    )
}


@Composable
fun AgesLabel() {
    AppText(
        color = MaterialTheme.colorScheme.primary,
        text = R.string.input_ages,
        textType = TextType.LABEL_LARGE
    )
}


@Composable
fun PercentageLabel() {
    AppText(
        color = MaterialTheme.colorScheme.primary,
        text = "%",
        textType = TextType.LABEL_LARGE
    )
}


@Composable
@Preview
fun AddUserScreen() {
    val viewModel: AddUserViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp, end = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(36.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton(
                onClick = {

                }
            )

            AppText(
                textType = TextType.HEADLINE_MEDIUM,
                textAlignment = TextAlign.Center,
                text = "title",
            )

            NextButton(
                onClick = {

                }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier =
            Modifier
                .fillMaxHeight(0.85f)
                .verticalScroll(rememberScrollState())
        )
        {
            CustomTextField(
                value = viewModel.userAccountDataState.name,
                errorMessage = viewModel.userAccountDataState.nameError,
                onValueChange = {
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnNameChanged(it)
                    )
                },
                placeholder = R.string.input_full_name_placeholder,
                label = R.string.input_full_name
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userAccountDataState.email,
                errorMessage = viewModel.userAccountDataState.emailError,
                onValueChange = {
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnEmailChanged(it)
                    )
                },
                placeholder = R.string.input_email_placeholder,
                label = R.string.input_email
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userAccountDataState.password,
                errorMessage = viewModel.userAccountDataState.passwordError,
                onValueChange = {
                    viewModel.onUserDataEvent(
                        UserAccountFormEvent.OnPasswordChanged(it)
                    )
                },
                placeholder = R.string.input_password_placeholder,
                label = R.string.input_password
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    CustomTextField(
                        value = viewModel.userPhysicalEvaluationDataState.height,
                        errorMessage = viewModel.userPhysicalEvaluationDataState.heightError,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {
                            viewModel.onPhysicalEvaluationDataEvent(
                                UserPhysicalEvaluationFormEvent.OnHeightChanged(it)
                            )
                        },
                        trailingIcon = { CmLabel() },
                        placeholder = R.string.input_height_placeholder,
                        label = R.string.input_height
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                Row(
                    modifier = Modifier.weight(1f)

                ) {
                    CustomTextField(
                        value = viewModel.userPhysicalEvaluationDataState.weight,
                        errorMessage = viewModel.userPhysicalEvaluationDataState.weightError,
                        keyboardType = KeyboardType.Number,
                        onValueChange = {
                            viewModel.onPhysicalEvaluationDataEvent(
                                UserPhysicalEvaluationFormEvent.OnWeightChanged(it)
                            )
                        },
                        trailingIcon = { KgLabel() },
                        placeholder = R.string.input_weight_placeholder,
                        label = R.string.input_weight
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.age,
                errorMessage = viewModel.userPhysicalEvaluationDataState.ageError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnAgeChanged(it)
                    )
                },
                trailingIcon = { AgesLabel() },
                placeholder = R.string.input_age_placeholder,
                label = R.string.input_age
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.armCircumference,
                errorMessage = viewModel.userPhysicalEvaluationDataState.armCircumferenceError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnArmCircumferenceChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
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
                        UserPhysicalEvaluationFormEvent.OnWaistCircumferenceChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
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
                        UserPhysicalEvaluationFormEvent.OnAbdomenCircumferenceChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
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
                        UserPhysicalEvaluationFormEvent.OnHipCircumferenceChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
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
                        UserPhysicalEvaluationFormEvent.OnThighCircumferenceChanged(it)
                    )
                },
                trailingIcon = {

                },
                placeholder = R.string.input_thigh_circumference_placeholder,
                label = R.string.input_thigh_circumference
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.scapularFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.scapularFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnScapularFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_scapular_fold_placeholder,
                label = R.string.input_scapular_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.legCircumference,
                errorMessage = viewModel.userPhysicalEvaluationDataState.legCircumferenceError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_leg_circumference_placeholder,
                label = R.string.input_leg_circumference
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.tricepsFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.tricepsFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnTricepsFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_triceps_fold_placeholder,
                label = R.string.input_triceps_fold
            )

            Spacer(modifier = Modifier.height(16.dp))


            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.bicepsFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.bicepsFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnBicepsFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_biceps_fold_placeholder,
                label = R.string.input_biceps_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.chestFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.chestFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnChestFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_chest_fold_placeholder,
                label = R.string.input_chest_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.axialFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.axialFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnAxialFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_axial_fold_placeholder,
                label = R.string.input_axial_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.suprailiacFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.suprailiacFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnSuprailiacFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_suprailiac_fold_placeholder,
                label = R.string.input_suprailiac_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.abdominalFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.abdominalFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnAbdominalFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_abdominal_fold_placeholder,
                label = R.string.input_abdominal_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.thighFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.thighFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnThighFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_thigh_fold_placeholder,
                label = R.string.input_thigh_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.legFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.legFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnLegFoldChanged(it)
                    )
                },
                trailingIcon = { MmLabel() },
                placeholder = R.string.input_leg_fold_placeholder,
                label = R.string.input_leg_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.healthIssue,
                errorMessage = viewModel.userPhysicalEvaluationDataState.healthIssueError,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnHealthIssueChanged(it)
                    )
                },
                placeholder = R.string.input_health_issue_placeholder,
                label = R.string.input_health_issue
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.fatPercentage,
                errorMessage = viewModel.userPhysicalEvaluationDataState.fatPercentageError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnFatPercentageChanged(it)
                    )
                },
                trailingIcon = { PercentageLabel() },
                placeholder = R.string.input_fat_percentage_placeholder,
                label = R.string.input_fat_percentage
            )

            Spacer(modifier = Modifier.height(16.dp))

            GroupedLabeledCheckbox(
                title = R.string.input_smoker,
                isChecked = viewModel.userPhysicalEvaluationDataState.exerciseExperience,
                checkedLabel = R.string.yes,
                unCheckedLabel = R.string.no,
                onCheckedChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnExerciseExperienceChanged(it)
                    )
                }
            )

            GroupedLabeledCheckbox(
                title = R.string.input_spine_problem,
                isChecked = viewModel.userPhysicalEvaluationDataState.spineProblem,
                checkedLabel = R.string.yes,
                unCheckedLabel = R.string.no,
                onCheckedChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnSpineProblemChanged(it)
                    )
                }
            )

            GroupedLabeledCheckbox(
                title = R.string.input_smoker,
                isChecked = viewModel.userPhysicalEvaluationDataState.isSmoker,
                checkedLabel = R.string.yes,
                unCheckedLabel = R.string.no,
                onCheckedChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnSmokerChanged(it)
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        AppButton(
            text = R.string.button_register,
            onClick = {
                viewModel.onPhysicalEvaluationDataEvent(
                    UserPhysicalEvaluationFormEvent.OnSubmit
                )
            }
        )
    }
}
