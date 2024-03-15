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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppDropdown
import com.ufape.shaypado.ui.components.AppHeader
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.Camera
import com.ufape.shaypado.ui.components.CameraButton
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.DropdownItem
import com.ufape.shaypado.ui.components.GroupedLabeledCheckbox
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.screens.signUp.UserPhysicalEvaluationFormEvent
import com.ufape.shaypado.ui.screens.trainer.createUser.AgesLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.CmLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.KgLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.MmLabel
import com.ufape.shaypado.ui.screens.trainer.createUser.PercentageLabel
import com.ufape.shaypado.ui.screens.trainer.studentDetails.StudentDetailsViewModel
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.compressImage
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun WorkoutSheetScreen(
    navController: NavController,
    showSnackBar: (String) -> Unit,
    studentId : String
) {
    val viewModel = hiltViewModel<StudentDetailsViewModel>()
    val context = LocalContext.current

    var showCamera by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        viewModel.fetchStudent(studentId)
    }

    LaunchedEffect(key1 = viewModel.studentData) {
        viewModel.studentData.collect {
            if (it is Result.Error) {
                showSnackBar(it.exception.getErrorMessage(context))
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
            enabled = false,
            label = R.string.input_user_name,
            value = viewModel.physicalFormState.name,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        )
        {
            Row (
                modifier = Modifier.weight(1f)
            ) {
                CustomTextField(
                    value = viewModel.physicalFormState.fatPercentage,
                    errorMessage = viewModel.physicalFormState.fatPercentageError,
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        viewModel.onUserDataEvent(
                            UserPhysicalEvaluationFormEvent.OnFatPercentageChanged(it)
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

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                CustomTextField(
                    value = viewModel.physicalFormState.height,
                    errorMessage = viewModel.physicalFormState.heightError,
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
                    value = viewModel.physicalFormState.weight,
                    errorMessage = viewModel.physicalFormState.weightError,
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
                    value = viewModel.physicalFormState.age,
                    errorMessage = viewModel.physicalFormState.ageError,
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
                    error = viewModel.physicalFormState.genderError,
                    selectedValue = viewModel.physicalFormState.gender,
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
            value = viewModel.physicalFormState.armCircumference,
            errorMessage = viewModel.physicalFormState.armCircumferenceError,
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
            value = viewModel.physicalFormState.waistCircumference,
            errorMessage = viewModel.physicalFormState.waistCircumferenceError,
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
            value = viewModel.physicalFormState.abdomenCircumference,
            errorMessage = viewModel.physicalFormState.abdomenCircumferenceError,
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
            value = viewModel.physicalFormState.hipCircumference,
            errorMessage = viewModel.physicalFormState.hipCircumferenceError,
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
            value = viewModel.physicalFormState.thighCircumference,
            errorMessage = viewModel.physicalFormState.thighCircumferenceError,
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
            value = viewModel.physicalFormState.scapularFold,
            errorMessage = viewModel.physicalFormState.scapularFoldError,
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
            value = viewModel.physicalFormState.legCircumference,
            errorMessage = viewModel.physicalFormState.legCircumferenceError,
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
            value = viewModel.physicalFormState.tricepsFold,
            errorMessage = viewModel.physicalFormState.tricepsFoldError,
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
            value = viewModel.physicalFormState.bicepsFold,
            errorMessage = viewModel.physicalFormState.bicepsFoldError,
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
            value = viewModel.physicalFormState.chestFold,
            errorMessage = viewModel.physicalFormState.chestFoldError,
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
            value = viewModel.physicalFormState.axialFold,
            errorMessage = viewModel.physicalFormState.axialFoldError,
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
            value = viewModel.physicalFormState.suprailiacFold,
            errorMessage = viewModel.physicalFormState.suprailiacFoldError,
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
            value = viewModel.physicalFormState.abdominalFold,
            errorMessage = viewModel.physicalFormState.abdominalFoldError,
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
            value = viewModel.physicalFormState.thighFold,
            errorMessage = viewModel.physicalFormState.thighFoldError,
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
            value = viewModel.physicalFormState.legFold,
            errorMessage = viewModel.physicalFormState.legFoldError,
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
            value = viewModel.physicalFormState.healthIssue,
            errorMessage = viewModel.physicalFormState.healthIssueError,
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

        GroupedLabeledCheckbox(
            title = R.string.input_smoker,
            isChecked = viewModel.physicalFormState.exerciseExperience,
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
            isChecked = viewModel.physicalFormState.spineProblem,
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
            isChecked = viewModel.physicalFormState.isSmoker,
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