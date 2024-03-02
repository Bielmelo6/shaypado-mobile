package com.ufape.shaypado.ui.screens.trainer.createUser

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

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
    navController: NavController,
    showSnackBar: (String) -> Unit
) {
    var shouldShowForm by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val createUserViewModel: CreateUserViewModel = hiltViewModel()

    if (!shouldShowForm) {
        CounterBase(
            navController = navController,
            title = "Quantos alunos você deseja cadastrar?",
            value = createUserViewModel.numberOfStudents + 1,
            decrease = {
                createUserViewModel.decreaseStudents()
            },
            increase = {
                createUserViewModel.increaseStudents()
            },
            onNextPressed = {
                createUserViewModel.allocateStudents()
                shouldShowForm = true
            }
        )
        return
    }

    BackHandler {
        shouldShowForm = false
    }

    LaunchedEffect(key1 = createUserViewModel.createUserEvent){
        createUserViewModel.createUserEvent.collect{
            if (it is Result.Success) {
                showSnackBar("Usuários cadastrados com sucesso")
                navController.popBackStack()
            } else if (it is Result.Error){
                showSnackBar(it.exception.getErrorMessage(context))
            }
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackButton(
            variant = ButtonVariant.PRIMARY,
            enabled = createUserViewModel.selectedStudent != 0
        ) {
            createUserViewModel.decreaseSelectedStudent()
        }

        AppText(
            text = String.format("%02d", createUserViewModel.selectedStudent + 1),
            textAlignment = TextAlign.Center,
            textType = TextType.DISPLAY_SMALL
        )

        NextButton(
            variant = ButtonVariant.PRIMARY,
            enabled = createUserViewModel.selectedStudent != createUserViewModel.numberOfStudents - 1
        ) {
            createUserViewModel.increaseSelectedStudent()
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
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].name,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].nameError,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnNameChanged(it)
                )
            },
            placeholder = R.string.input_full_name_placeholder,
            label = R.string.input_full_name
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].email,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].emailError,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnEmailChanged(it)
                )
            },
            placeholder = R.string.input_email_placeholder,
            label = R.string.input_email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].password,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].passwordError,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
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
                    value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].height,
                    errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].heightError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        createUserViewModel.onUserDataEvent(
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
                    value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].weight,
                    errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].weightError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        createUserViewModel.onUserDataEvent(
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
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].age,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].ageError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAgeChanged(it)
                )
            },
            trailingIcon = { AgesLabel() },
            placeholder = R.string.input_age_placeholder,
            label = R.string.input_age
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].armCircumference,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].armCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnArmCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_arm_circumference_placeholder,
            label = R.string.input_arm_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].waistCircumference,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].waistCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnWaistCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_waist_circumference_placeholder,
            label = R.string.input_waist_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].abdomenCircumference,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].abdomenCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAbdomenCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_abdomen_circumference_placeholder,
            label = R.string.input_abdomen_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].hipCircumference,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].hipCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnHipCircumferenceChanged(it)
                )
            },
            trailingIcon = { CmLabel() },
            placeholder = R.string.input_hip_circumference_placeholder,
            label = R.string.input_hip_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].thighCircumference,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].thighCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
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
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].scapularFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].scapularFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnScapularFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_scapular_fold_placeholder,
            label = R.string.input_scapular_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].legCircumference,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].legCircumferenceError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnLegCircumferenceChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_leg_circumference_placeholder,
            label = R.string.input_leg_circumference
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].tricepsFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].tricepsFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnTricepsFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_triceps_fold_placeholder,
            label = R.string.input_triceps_fold
        )

        Spacer(modifier = Modifier.height(16.dp))


        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].bicepsFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].bicepsFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnBicepsFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_biceps_fold_placeholder,
            label = R.string.input_biceps_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].chestFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].chestFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnChestFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_chest_fold_placeholder,
            label = R.string.input_chest_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].axialFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].axialFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAxialFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_axial_fold_placeholder,
            label = R.string.input_axial_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].suprailiacFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].suprailiacFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnSuprailiacFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_suprailiac_fold_placeholder,
            label = R.string.input_suprailiac_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].abdominalFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].abdominalFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnAbdominalFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_abdominal_fold_placeholder,
            label = R.string.input_abdominal_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].thighFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].thighFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnThighFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_thigh_fold_placeholder,
            label = R.string.input_thigh_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].legFold,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].legFoldError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnLegFoldChanged(it)
                )
            },
            trailingIcon = { MmLabel() },
            placeholder = R.string.input_leg_fold_placeholder,
            label = R.string.input_leg_fold
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].healthIssue,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].healthIssueError,
            keyboardType = KeyboardType.Text,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnHealthIssueChanged(it)
                )
            },
            placeholder = R.string.input_health_issue_placeholder,
            label = R.string.input_health_issue
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = createUserViewModel.studentsData[createUserViewModel.selectedStudent].fatPercentage,
            errorMessage = createUserViewModel.studentsData[createUserViewModel.selectedStudent].fatPercentageError,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                createUserViewModel.onUserDataEvent(
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
            isChecked = createUserViewModel.studentsData[createUserViewModel.selectedStudent].exerciseExperience,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnExerciseExperienceChanged(it)
                )
            }
        )

        GroupedLabeledCheckbox(
            title = R.string.input_spine_problem,
            isChecked = createUserViewModel.studentsData[createUserViewModel.selectedStudent].spineProblem,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnSpineProblemChanged(it)
                )
            }
        )

        GroupedLabeledCheckbox(
            title = R.string.input_smoker,
            isChecked = createUserViewModel.studentsData[createUserViewModel.selectedStudent].isSmoker,
            checkedLabel = R.string.yes,
            unCheckedLabel = R.string.no,
            onCheckedChange = {
                createUserViewModel.onUserDataEvent(
                    UserFormEvent.OnSmokerChanged(it)
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

    AppButton(
        text = R.string.button_register,
        onClick = {
            createUserViewModel.onUserDataEvent(
                UserFormEvent.OnSubmit
            )
        }
    )

}
