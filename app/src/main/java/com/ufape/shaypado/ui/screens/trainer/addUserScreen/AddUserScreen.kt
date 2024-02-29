package com.ufape.shaypado.ui.screens.trainer.addUserScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.BackButton
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.GroupedLabeledCheckbox
import com.ufape.shaypado.ui.components.NextButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.screens.trainer.counter.CounterBase

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
fun AddUserScreen(
    navController: NavController
) {
    var shouldShowForm by remember { mutableStateOf(false) }

    val addUserViewModel: AddUserViewModel = hiltViewModel()

    if (!shouldShowForm) {
        CounterBase(
            navController = navController,
            title = "Quantos alunos você deseja cadastrar?",
            value = addUserViewModel.numberOfStudents + 1,
            decrease = {
                addUserViewModel.decreaseStudents()
            },
            increase = {
                addUserViewModel.increaseStudents()
            },
            onNextPressed = {
                addUserViewModel.allocateStudents()
                shouldShowForm = true
            }
        )
        return
    }

    BackHandler {
        shouldShowForm = false
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(
            variant = ButtonVariant.PRIMARY,
            enabled = addUserViewModel.selectedStudent != 0
        ) {
            addUserViewModel.decreaseSelectedStudent()
        }

        AppText(
            text = String.format("%02d", addUserViewModel.selectedStudent + 1),
            textAlignment = TextAlign.Center,
            textType = TextType.DISPLAY_SMALL
        )

        NextButton(
            variant = ButtonVariant.PRIMARY,
            enabled = addUserViewModel.selectedStudent != addUserViewModel.numberOfStudents - 1
        ) {
            addUserViewModel.increaseSelectedStudent()
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier =
        Modifier
            .fillMaxHeight(0.9f)
            .verticalScroll(rememberScrollState())
    )
    {
        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].name,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].nameError,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnNameChanged(it)
                )
            },
            placeholder = R.string.input_full_name_placeholder,
            label = R.string.input_full_name
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].email,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].emailError,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnEmailChanged(it)
                )
            },
            placeholder = R.string.input_email_placeholder,
            label = R.string.input_email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].password,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].passwordError,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnPasswordChanged(it)
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
                    value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].height,
                    errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].heightError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        addUserViewModel.onUserDataEvent(
                            UserFormEvent.OnHeightChanged(it)
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
                    value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].weight,
                    errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].weightError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        addUserViewModel.onUserDataEvent(
                            UserFormEvent.OnWeightChanged(it)
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
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].age,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].ageError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAgeChanged(it)
                )
            },
            trailingIcon = { AgesLabel() },
            placeholder = R.string.input_age_placeholder,
            label = R.string.input_age
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].armCircumference,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].armCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnArmCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_arm_circumference_placeholder,
            label = R.string.input_arm_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].waistCircumference,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].waistCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnWaistCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_waist_circumference_placeholder,
            label = R.string.input_waist_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].abdomenCircumference,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].abdomenCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAbdomenCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_abdomen_circumference_placeholder,
            label = R.string.input_abdomen_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].hipCircumference,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].hipCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnHipCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_hip_circumference_placeholder,
            label = R.string.input_hip_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].thighCircumference,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].thighCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnThighCircumferenceChanged(it)
                )
            },
            trailingIcon = {

            },
            placeholder = R.string.input_thigh_circumference_placeholder,
            label = R.string.input_thigh_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].scapularFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].scapularFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnScapularFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_scapular_fold_placeholder,
            label = R.string.input_scapular_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].legCircumference,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].legCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnLegCircumferenceChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_leg_circumference_placeholder,
            label = R.string.input_leg_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].tricepsFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].tricepsFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnTricepsFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_triceps_fold_placeholder,
            label = R.string.input_triceps_fold
        )

        Spacer(modifier = Modifier.height(16.dp))


        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].bicepsFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].bicepsFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnBicepsFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_biceps_fold_placeholder,
            label = R.string.input_biceps_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].chestFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].chestFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnChestFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_chest_fold_placeholder,
            label = R.string.input_chest_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].axialFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].axialFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAxialFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_axial_fold_placeholder,
            label = R.string.input_axial_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].suprailiacFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].suprailiacFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnSuprailiacFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_suprailiac_fold_placeholder,
            label = R.string.input_suprailiac_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].abdominalFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].abdominalFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAbdominalFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_abdominal_fold_placeholder,
            label = R.string.input_abdominal_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].thighFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].thighFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnThighFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_thigh_fold_placeholder,
            label = R.string.input_thigh_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].legFold,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].legFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnLegFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_leg_fold_placeholder,
            label = R.string.input_leg_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].healthIssue,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].healthIssueError,
            keyboardType = KeyboardType.Text,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnHealthIssueChanged(it)
                )
            },
            placeholder = R.string.input_health_issue_placeholder,
            label = R.string.input_health_issue
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addUserViewModel.studentsData[addUserViewModel.selectedStudent].fatPercentage,
            errorMessage = addUserViewModel.studentsData[addUserViewModel.selectedStudent].fatPercentageError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnFatPercentageChanged(it)
                )
            },
            trailingIcon = { PercentageLabel() },
            placeholder = R.string.input_fat_percentage_placeholder,
            label = R.string.input_fat_percentage
        )

        Spacer(modifier = Modifier.height(16.dp))

        GroupedLabeledCheckbox(
            title = R.string.input_smoker,
            isChecked = addUserViewModel.studentsData[addUserViewModel.selectedStudent].exerciseExperience,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnExerciseExperienceChanged(it)
                )
            }
        )

        GroupedLabeledCheckbox(
            title = R.string.input_spine_problem,
            isChecked = addUserViewModel.studentsData[addUserViewModel.selectedStudent].spineProblem,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnSpineProblemChanged(it)
                )
            }
        )

        GroupedLabeledCheckbox(
            title = R.string.input_smoker,
            isChecked = addUserViewModel.studentsData[addUserViewModel.selectedStudent].isSmoker,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                addUserViewModel.onUserDataEvent(
                    UserFormEvent.OnSmokerChanged(it)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

    AppButton(
        text = R.string.button_register,
        onClick = {
            addUserViewModel.onUserDataEvent(
                UserFormEvent.OnSubmit
            )
        }
    )

}
