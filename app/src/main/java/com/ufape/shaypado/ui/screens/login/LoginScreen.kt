package com.ufape.shaypado.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.RoundedButton
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.theme.AtIcon
import com.ufape.shaypado.ui.theme.GoogleImage
import com.ufape.shaypado.ui.theme.KeyIcon
import com.ufape.shaypado.ui.theme.ShaypadoImage

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .padding(top = 102.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            ShaypadoImage()

            Spacer(modifier = Modifier.height(80.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(20.dp)
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RoundedButton(onClick = {
                        navController.popBackStack()
                    })
                    Spacer(modifier = Modifier.width(16.dp))
                    AppText(TextType.TITLE_MEDIUM, text = R.string.sign_in_title)
                }
                Spacer(modifier = Modifier.height(100.dp))
                CustomTextField(
                    keyboardType = KeyboardType.Email,
                    leadingIcon = { AtIcon() },
                    placeholder = R.string.input_email
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    keyboardType = KeyboardType.Password,
                    leadingIcon = { KeyIcon() },
                    placeholder = R.string.input_password
                )
                Spacer(modifier = Modifier.height(50.dp))
                AppButton(
                    text = R.string.button_login,
                    onClick = {
                        navController.navigate(MobileNavigationScreen.NavRoot.route) {
                            popUpTo(AuthNavigationScreen.NavRoot.route) {
                                inclusive = true
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                AppButton(
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    text = R.string.button_google,
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(MobileNavigationScreen.NavRoot.route)
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GoogleImage()
                        Spacer(modifier = Modifier.width(11.dp))
                        AppText(textType = TextType.LABEL_MEDIUM, R.string.button_google)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    stringResource(id = R.string.sign_up_forgot_password),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(AuthNavigationScreen.ForgotPassword.route)
                        }
                )
            }
        }
    }
}