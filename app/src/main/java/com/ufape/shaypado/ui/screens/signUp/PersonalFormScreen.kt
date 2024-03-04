package com.ufape.shaypado.ui.screens.signUp

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppSnackBar
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.createTempPdfFileFromUri
import com.ufape.shaypado.util.getErrorMessage
import java.io.File

@Composable
fun PersonalFormScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {
    var snackbarMessage: String? by remember { mutableStateOf(null) }
    val context = LocalContext.current

    fun onFile ( file : File) {
        viewModel.onPersonalDataEvent(
            PersonalFormEvent.OnPlansDocumentChanged(
                file.path
            )
        )
    }

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                if (uri != null) {
                    onFile(uri.createTempPdfFileFromUri(context)!!)
                }
            } else {
                Log.d("MediaPicker", "No file selected")
            }
        }


    fun launchDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "*/*"
            putExtra(
                Intent.EXTRA_MIME_TYPES, arrayOf(
                    "application/pdf"
                )
            )
        }
        pickMedia.launch(intent)
    }

    LaunchedEffect(key1 = viewModel.trainerRegisterEvent) {
        viewModel.trainerRegisterEvent.collect {
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
                viewModel.onPersonalDataEvent(PersonalFormEvent.OnSubmit)
            },
            navController = navController,
            topTitleSpacing = 16
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Card(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clickable {
                            navController.navigate(AuthNavigationScreen.SignUpCamera.route)
                        },
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(4.dp, MaterialTheme.colorScheme.primary),
                    colors = CardDefaults.cardColors(
                        contentColor = Color.Red
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (viewModel.personalFormDataState.profilePicture != null) {
                            val file = viewModel.personalFormDataState.profilePicture?.let { File(it) }

                            if (file != null) {
                                Image(
                                    rememberImagePainter(file),
                                    contentScale = ContentScale.FillWidth,
                                    contentDescription = "...",
                                )
                            }
                        } else {
                            Icon(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(30.dp),
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Camera",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                }

                AppText(
                    text = R.string.input_profile_picture,
                    textType = TextType.LABEL_LARGE,
                )

            }

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                value = viewModel.personalFormDataState.name,
                errorMessage = viewModel.personalFormDataState.nameError,
                onValueChange = {
                    viewModel.onPersonalDataEvent(
                        PersonalFormEvent.OnNameChanged(
                            it
                        )
                    )
                },
                placeholder = R.string.input_full_name_placeholder,
                label = R.string.input_full_name
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.personalFormDataState.email,
                errorMessage = viewModel.personalFormDataState.emailError,
                onValueChange = {
                    viewModel.onPersonalDataEvent(
                        PersonalFormEvent.OnEmailChanged(
                            it
                        )
                    )
                },
                placeholder = R.string.input_contact_email,
                label = R.string.input_contact_email_placeholder
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.personalFormDataState.phone,
                errorMessage = viewModel.personalFormDataState.phoneError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPersonalDataEvent(
                        PersonalFormEvent.OnPhoneChanged(
                            it
                        )
                    )
                },
                placeholder = R.string.input_phone_placeholder,
                label = R.string.input_phone
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.personalFormDataState.specialties,
                errorMessage = viewModel.personalFormDataState.specialtiesError,
                onValueChange = {
                    viewModel.onPersonalDataEvent(
                        PersonalFormEvent.OnSpecialtiesChanged(
                            it
                        )
                    )
                },
                placeholder = R.string.input_specialties_placeholder,
                label = R.string.input_specialties
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.personalFormDataState.age,
                errorMessage = viewModel.personalFormDataState.ageError,
                keyboardType = KeyboardType.Number,
                onValueChange = {
                    viewModel.onPersonalDataEvent(
                        PersonalFormEvent.OnAgeChanged(
                            it
                        )
                    )
                },
                placeholder = R.string.input_age_placeholder,
                label = R.string.input_age
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.weight(1f)
                ) {
                    CustomTextField(
                        value = viewModel.personalFormDataState.state,
                        errorMessage = viewModel.personalFormDataState.stateError,
                        onValueChange = {
                            viewModel.onPersonalDataEvent(
                                PersonalFormEvent.OnStateChanged(
                                    it
                                )
                            )
                        },
                        placeholder = R.string.input_state_placeholder,
                        label = R.string.input_state
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Row(
                    modifier = Modifier.weight(1f)
                )
                {
                    CustomTextField(
                        value = viewModel.personalFormDataState.city,
                        errorMessage = viewModel.personalFormDataState.cityError,
                        onValueChange = {
                            viewModel.onPersonalDataEvent(
                                PersonalFormEvent.OnCityChanged(
                                    it
                                )
                            )
                        },
                        placeholder = R.string.input_city_placeholder,
                        label = R.string.input_city
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = viewModel.personalFormDataState.workLocation,
                errorMessage = viewModel.personalFormDataState.workLocationError,
                onValueChange = {
                    viewModel.onPersonalDataEvent(
                        PersonalFormEvent.OnWorkLocationChanged(
                            it
                        )
                    )
                },
                placeholder = R.string.input_work_location_placeholder,
                label = R.string.input_work_location
            )

            Spacer(modifier = Modifier.height(16.dp))

            val plansDocumentNameA = viewModel.personalFormDataState.plansDocument?.split("/")
            val plansDocument = plansDocumentNameA?.get(plansDocumentNameA.size - 1) ?: stringResource(id = R.string.input_plans_document_placeholder)
            AppButton(
                variant = ButtonVariant.SECONDARY_CONTAINER,
                text = plansDocument,
                onClick = {
                    launchDocumentPicker()
                },
                leftIcon = {
                    if (viewModel.personalFormDataState.plansDocument != null) {
                        Icon(
                            imageVector = Icons.Default.UploadFile,
                            contentDescription = "File",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                },
                errorMessage = viewModel.personalFormDataState.plansDocumentError
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}