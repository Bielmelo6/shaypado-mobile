package com.ufape.shaypado.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.SnackBar
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.theme.EmailIcon
import com.ufape.shaypado.ui.theme.GoogleImage
import com.ufape.shaypado.ui.theme.KeyIcon
import com.ufape.shaypado.ui.theme.ShaypadoImage
import com.ufape.shaypado.util.Result
import com.ufape.shaypado.util.getErrorMessage

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    var snackBarMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.loginEvent.collect {
            if (it is Result.Success) {
                navController.navigate(MobileNavigationScreen.NavRoot.route) {
                    popUpTo(AuthNavigationScreen.NavRoot.route) {
                        inclusive = true
                    }
                }
            } else if (it is Result.Error) {
                snackBarMessage = it.exception.getErrorMessage(context)

            }
        }
    }

    SnackBar(
        message = snackBarMessage,
        reset = { snackBarMessage = null }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                )
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                ShaypadoImage()

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        )
                        .padding(16.dp)
                )
                {
                    AppText(TextType.HEADLINE_MEDIUM, text = R.string.sign_in_title)

                    Spacer(modifier = Modifier.height(32.dp))

                    CustomTextField(
                        value = viewModel.loginDataState.email,
                        onValueChange = {
                            viewModel.onLoginDataEvent(LoginFormEvent.OnEmailChanged(it))
                        },
                        errorMessage = viewModel.loginDataState.emailError,
                        keyboardType = KeyboardType.Email,
                        leadingIcon = { EmailIcon() },
                        placeholder = R.string.input_email_placeholder,
                        label = R.string.input_email
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomTextField(
                        value = viewModel.loginDataState.password,
                        onValueChange = {
                            viewModel.onLoginDataEvent(LoginFormEvent.OnPasswordChanged(it))
                        },
                        errorMessage = viewModel.loginDataState.passwordError,
                        keyboardType = KeyboardType.Password,
                        leadingIcon = { KeyIcon() },
                        placeholder = R.string.input_password_placeholder,
                        label = R.string.input_password
                    )
                    TextButton(onClick = {
                        navController.navigate(AuthNavigationScreen.ForgotPassword.route)
                    }) {
                        AppText(TextType.BODY_MEDIUM, text = R.string.sign_up_forgot_password)
                    }
                    AppButton(
                        text = R.string.button_login,
                        onClick = {
                            viewModel.onLoginDataEvent(LoginFormEvent.OnSubmit)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AppButton(
                        variant = ButtonVariant.TERTIARY,
                        text = R.string.button_google,
                        onClick = {
                            navController.popBackStack()
                            navController.navigate(MobileNavigationScreen.NavRoot.route)
                        }
                    ) {
                        GoogleImage()
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AppText(TextType.BODY_MEDIUM, text = R.string.sign_up_no_account)
                        TextButton(onClick = {
                            navController.navigate(AuthNavigationScreen.SignUpUserForm.route)
                        }) {
                            AppText(
                                TextType.BODY_MEDIUM,
                                text = R.string.sign_up_label,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}