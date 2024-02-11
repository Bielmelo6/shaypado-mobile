package com.ufape.shaypado.ui.screens.signUp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.theme.EmailIcon
import com.ufape.shaypado.ui.theme.KeyIcon
import com.ufape.shaypado.ui.theme.PersonIcon
import com.ufape.shaypado.util.Result

@Composable
fun UserFormScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SignUpViewModel>()

    LaunchedEffect(key1 = viewModel.validationStatus ){
        viewModel.validationStatus.collect {
            if (it is Result.Success) {
                navController.navigate(AuthNavigationScreen.SignUpChooseProfile.route)
                viewModel.resetValidationStatus()
            }
        }
    }

    SignUpScreenBase(
        title = R.string.sign_up_title,
        buttonText = R.string.button_next,
        onButtonClicked = {
            viewModel.onUserDataEvent(UserAccountFormEvent.ValidateUserData)
        },
        navController = navController
    ){
        CustomTextField(
            value = viewModel.userAccountDataState.name,
            errorMessage = viewModel.userAccountDataState.nameError,
            onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnNameChanged(it)) },
            leadingIcon = { PersonIcon() },
            label = R.string.input_user_name,
            placeholder = R.string.input_user_name_placeholder
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userAccountDataState.email,
            errorMessage = viewModel.userAccountDataState.emailError,
            keyboardType = KeyboardType.Email,
            onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnEmailChanged(it)) },
            leadingIcon = { EmailIcon() },
            placeholder = R.string.input_email_placeholder,
            label = R.string.input_email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userAccountDataState.emailConfirmation,
            errorMessage = viewModel.userAccountDataState.emailConfirmationError,
            keyboardType = KeyboardType.Email,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserAccountFormEvent.OnEmailConfirmationChanged(
                        it
                    )
                )
            },
            leadingIcon = { EmailIcon() },
            placeholder = R.string.input_email_confirmation_placeholder,
            label = R.string.input_email_confirmation
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userAccountDataState.password,
            errorMessage = viewModel.userAccountDataState.passwordError,
            keyboardType = KeyboardType.Password,
            onValueChange = { viewModel.onUserDataEvent(UserAccountFormEvent.OnPasswordChanged(it)) },
            leadingIcon = { KeyIcon() },
            placeholder = R.string.input_password_placeholder,
            label = R.string.input_password
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = viewModel.userAccountDataState.passwordConfirmation,
            errorMessage = viewModel.userAccountDataState.passwordConfirmationError,
            onValueChange = {
                viewModel.onUserDataEvent(
                    UserAccountFormEvent.OnPasswordConfirmationChanged(
                        it
                    )
                )
            },
            keyboardType = KeyboardType.Password,
            leadingIcon = { KeyIcon() },
            placeholder = R.string.input_password_confirmation_placeholder,
            label = R.string.input_password_confirmation
        )
    }
}
