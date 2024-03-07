package com.ufape.shaypado.ui.screens.trainer.workoutSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppDropdown
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.DropdownItem
import com.ufape.shaypado.ui.components.GroupedLabeledCheckbox
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormEvent
import com.ufape.shaypado.ui.screens.trainer.createUser.AgesLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.CmLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.KgLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.MmLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.PercentageLabel

@Composable
fun WorkoutSheetScreen(
    navController: NavController,
    showSnackBar: (String) -> Unit,
    studentId : String
) {
    val viewModel = hiltViewModel<WorkoutSheetViewModel>()
    AppHeader(navController = navController, title = R.string.workout_sheet)

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier =
        Modifier
            .fillMaxHeight(0.9f)
            .verticalScroll(rememberScrollState())
    )
    {
        CustomTextField(
            label = R.string.input_user_name,
            value = viewModel.userName,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomTextField(
                    value = viewModel.studentsData.height,
                    errorMessage = viewModel.studentsData.heightError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onUserDataEvent(
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
                    value = viewModel.studentsData.weight,
                    errorMessage = viewModel.studentsData.weightError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onUserDataEvent(
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


        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomTextField(
                    value = viewModel.studentsData.age,
                    errorMessage = viewModel.studentsData.ageError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onUserDataEvent(
                            UserPhysicalEvaluationFormEvent.OnAgeChanged(it)
                        )
                    },
                    trailingIcon = { AgesLabel() },
                    placeholder = R.string.input_age_placeholder,
                    label = R.string.input_age
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            Row(
                modifier = Modifier.weight(1f)

            ) {
                AppDropdown(
                    onItemSelected = { value, label ->
                        viewModel.onUserDataEvent(
                            UserPhysicalEvaluationFormEvent.OnGenderChanged(
                                value
                            )
                        )
                    },
                    label = "Gênero",
                    error = viewModel.studentsData.genderError,
                    selectedValue = viewModel.studentsData.gender,
                    items = listOf(
                        DropdownItem(
                            value = "M",
                            text = "Masculino"
                        ),
                        DropdownItem(
                            value = "F",
                            text = "Feminino"
                        )
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.armCircumference,
            errorMessage = viewModel.studentsData.armCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnArmCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_arm_circumference_placeholder,
            label = R.string.input_arm_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.waistCircumference,
            errorMessage = viewModel.studentsData.waistCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnWaistCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_waist_circumference_placeholder,
            label = R.string.input_waist_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.abdomenCircumference,
            errorMessage = viewModel.studentsData.abdomenCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnAbdomenCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_abdomen_circumference_placeholder,
            label = R.string.input_abdomen_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.hipCircumference,
            errorMessage = viewModel.studentsData.hipCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnHipCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_hip_circumference_placeholder,
            label = R.string.input_hip_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.thighCircumference,
            errorMessage = viewModel.studentsData.thighCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
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
            value = viewModel.studentsData.scapularFold,
            errorMessage = viewModel.studentsData.scapularFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnScapularFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_scapular_fold_placeholder,
            label = R.string.input_scapular_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.legCircumference,
            errorMessage = viewModel.studentsData.legCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_leg_circumference_placeholder,
            label = R.string.input_leg_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.tricepsFold,
            errorMessage = viewModel.studentsData.tricepsFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnTricepsFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_triceps_fold_placeholder,
            label = R.string.input_triceps_fold
        )

        Spacer(modifier = Modifier.height(16.dp))


        CustomTextField(
            value = viewModel.studentsData.bicepsFold,
            errorMessage = viewModel.studentsData.bicepsFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnBicepsFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_biceps_fold_placeholder,
            label = R.string.input_biceps_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.chestFold,
            errorMessage = viewModel.studentsData.chestFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnChestFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_chest_fold_placeholder,
            label = R.string.input_chest_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.axialFold,
            errorMessage = viewModel.studentsData.axialFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnAxialFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_axial_fold_placeholder,
            label = R.string.input_axial_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.suprailiacFold,
            errorMessage = viewModel.studentsData.suprailiacFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnSuprailiacFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_suprailiac_fold_placeholder,
            label = R.string.input_suprailiac_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.abdominalFold,
            errorMessage = viewModel.studentsData.abdominalFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnAbdominalFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_abdominal_fold_placeholder,
            label = R.string.input_abdominal_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.thighFold,
            errorMessage = viewModel.studentsData.thighFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnThighFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_thigh_fold_placeholder,
            label = R.string.input_thigh_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.legFold,
            errorMessage = viewModel.studentsData.legFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnLegFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_leg_fold_placeholder,
            label = R.string.input_leg_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.healthIssue,
            errorMessage = viewModel.studentsData.healthIssueError,
            keyboardType = KeyboardType.Text,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnHealthIssueChanged(it)
                )
            },
            placeholder = R.string.input_health_issue_placeholder,
            label = R.string.input_health_issue
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.studentsData.fatPercentage,
            errorMessage = viewModel.studentsData.fatPercentageError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                viewModel.onUserDataEvent(
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
            isChecked = viewModel.studentsData.exerciseExperience,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnExerciseExperienceChanged(it)
                )
            }
        )

        GroupedLabeledCheckbox(
            title = R.string.input_spine_problem,
            isChecked = viewModel.studentsData.spineProblem,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnSpineProblemChanged(it)
                )
            }
        )

        GroupedLabeledCheckbox(
            title = R.string.input_smoker,
            isChecked = viewModel.studentsData.isSmoker,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                viewModel.onUserDataEvent(
                    UserPhysicalEvaluationFormEvent.OnSmokerChanged(it)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

    AppButton(
        text = R.string.button_register,
        onClick = {
            viewModel.onUserDataEvent(
                UserPhysicalEvaluationFormEvent.OnSubmit
            )
        }
    )
}