package com.ufape.shaypado.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.FullWidthButton
import com.ufape.shaypado.ui.components.Label
import com.ufape.shaypado.ui.components.RoundedButton
import com.ufape.shaypado.ui.components.PasswordTextField
import com.ufape.shaypado.ui.components.PlainTextField
import com.ufape.shaypado.ui.components.Title
import com.ufape.shaypado.ui.routes.MobileNavigationScreen
import com.ufape.shaypado.ui.theme.AtIcon
import com.ufape.shaypado.ui.theme.KeyIcon

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.primary),
            )
            .padding(top = 102.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(id = R.drawable.ic_shaypado_name),
                contentDescription = "Shaypado"
            )

            Spacer(modifier = Modifier.height(80.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(20.dp)
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RoundedButton()
                    Spacer(modifier = Modifier.width(16.dp))
                    Title(stringResource(id = R.string.sign_in_title))
                }
                Spacer(modifier = Modifier.height(100.dp))
                PlainTextField(
                    keyboardType = KeyboardType.Email,
                    leadingIcon = { AtIcon() },
                    placeholder = stringResource(id = R.string.input_email)
                )
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(
                    leadingIcon = { KeyIcon() },
                    placeholder = stringResource(id = R.string.input_password)
                )
                Spacer(modifier = Modifier.height(50.dp))
                FullWidthButton(
                    text = stringResource(id = R.string.button_login),
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(MobileNavigationScreen.NavRoot.route)
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                FullWidthButton(
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    text = stringResource(id = R.string.button_google),
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(MobileNavigationScreen.NavRoot.route)
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google"
                        )
                        Spacer(modifier = Modifier.width(11.dp))
                        Label(stringResource(id = R.string.button_google))
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

                )
            }
        }
    }
}