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
@Preview
fun AddUserScreen() {
    val viewModel : AddUserViewModel = hiltViewModel()

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
                fillWidth = true
            )

            BackButton(
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
            // Exemplo para altura
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

            Spacer(modifier = Modifier.height(16.dp))

// Exemplo para idade
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.age,
                errorMessage = viewModel.userPhysicalEvaluationDataState.ageError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnAgeChanged(it)
                    )
                },
                placeholder = R.string.input_age_placeholder,
                label = R.string.input_age
            )

            Spacer(modifier = Modifier.height(16.dp))

// Exemplo para circunferência do quadril
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

            // Percentual de gordura
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.fatPercentage,
                errorMessage = viewModel.userPhysicalEvaluationDataState.fatPercentageError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnFatPercentageChanged(it)
                    )
                },
                placeholder = R.string.input_fat_percentage_placeholder,
                label = R.string.input_fat_percentage
            )

            Spacer(modifier = Modifier.height(16.dp))

// Circunferência do braço
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

// Circunferência da cintura
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

// Circunferência do abdômen
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

// Circunferência do quadril
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

// Circunferência da coxa
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

// Circunferência da perna
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.legCircumference,
                errorMessage = viewModel.userPhysicalEvaluationDataState.legCircumferenceError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnLegCircumferenceChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_leg_circumference_placeholder,
                label = R.string.input_leg_circumference
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Dobra tríceps
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.tricepsFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.tricepsFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnTricepsFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_triceps_fold_placeholder,
                label = R.string.input_triceps_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Dobra bíceps
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.bicepsFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.bicepsFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnBicepsFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_biceps_fold_placeholder,
                label = R.string.input_biceps_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Dobra torácica
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.chestFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.chestFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnChestFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_chest_fold_placeholder,
                label = R.string.input_chest_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Dobra axial
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.axialFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.axialFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnAxialFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_axial_fold_placeholder,
                label = R.string.input_axial_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Dobra suprailíaca
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.suprailiacFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.suprailiacFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnSuprailiacFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_suprailiac_fold_placeholder,
                label = R.string.input_suprailiac_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Dobra abdominal
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.abdominalFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.abdominalFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnAbdominalFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_abdominal_fold_placeholder,
                label = R.string.input_abdominal_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Dobra da coxa
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.thighFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.thighFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnThighFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_thigh_fold_placeholder,
                label = R.string.input_thigh_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Dobra de perna
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.legFold,
                errorMessage = viewModel.userPhysicalEvaluationDataState.legFoldError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnLegFoldChanged(it)
                    )
                },
                trailingIcon = { CmLabel() },
                placeholder = R.string.input_leg_fold_placeholder,
                label = R.string.input_leg_fold
            )

            Spacer(modifier = Modifier.height(16.dp))

// Problema de saúde
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

// Experiência na prática de exercícios
            CustomTextField(
                value = viewModel.userPhysicalEvaluationDataState.exerciseExperience,
                errorMessage = viewModel.userPhysicalEvaluationDataState.exerciseExperienceError,
                keyboardType = KeyboardType.Text,
                onValueChange = {
                    viewModel.onPhysicalEvaluationDataEvent(
                        UserPhysicalEvaluationFormEvent.OnExerciseExperienceChanged(it)
                    )
                },
                placeholder = R.string.input_exercise_experience_placeholder,
                label = R.string.input_exercise_experience
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        AppButton(
            text = "buttonText",
            onClick = {

            }
        )
    }
}
