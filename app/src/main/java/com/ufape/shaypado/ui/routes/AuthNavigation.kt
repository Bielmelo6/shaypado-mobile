package com.ufape.shaypado.ui.routes

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ufape.shaypado.R

sealed class AuthNavigationScreen(val route: String) {
    data object NavRoot : AuthNavigationScreen("auth")
    data object Login : AuthNavigationScreen("login")
    data object ForgotPassword : AuthNavigationScreen("forgot_password")
    data object SignUp : AuthNavigationScreen("sign_up")
}

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        route = AuthNavigationScreen.NavRoot.route,
        startDestination = AuthNavigationScreen.Login.route
    ) {
        composable(AuthNavigationScreen.Login.route) {
            Login(navController)
        }
        composable(AuthNavigationScreen.ForgotPassword.route) {
            ForgotPassword(navController)
        }
        composable(AuthNavigationScreen.SignUp.route) {
            SignUp(navController)
        }
    }

}

@Preview
@Composable
fun CustomTextField(
    keyboardType: KeyboardType = KeyboardType.Text,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: String = ""
) {
    var textState by remember { mutableStateOf("") }
    val containerColor = colorResource(id = R.color.box_light)
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = { textState = it },
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = leadingIcon,
    )
}

@Composable
@Preview
fun CustomPasswordTextField(
    leadingIcon: @Composable (() -> Unit)? = null
) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    val containerColor = colorResource(id = R.color.box_light)
    TextField(
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = leadingIcon,
        value = password,
        shape = RoundedCornerShape(15.dp),
        onValueChange = { password = it },
        singleLine = true,
        placeholder = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Close
            else Icons.Filled.Check

            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Preview
@Composable
fun CustomButton() {
    IconButton(

        modifier = Modifier
            .background(
                color = colorResource(id = R.color.primary),
                shape = CircleShape
            ),
        onClick = { /*TODO*/ }
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_at), contentDescription = "Google")
    }
}

@Composable
fun Login(navController: NavController) {
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
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    CustomButton()
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(100.dp))

                CustomTextField(keyboardType = KeyboardType.Email, leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_at),
                        contentDescription = "Email"
                    )
                }, placeholder = "Email")
                Spacer(modifier = Modifier.height(16.dp))
                CustomPasswordTextField(leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_key),
                        contentDescription = "Email"
                    )
                })

                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(text = "Entrar", onClick = {
                    navController.popBackStack()
                    navController.navigate(MobileNavigationScreen.NavRoot.route)
                })

                Spacer(modifier = Modifier.height(20.dp))

                CustomButton(
                    backgroundColor = colorResource(id = R.color.secondary),
                    text = "Login com o Google",
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(MobileNavigationScreen.NavRoot.route)
                    })
            }
        }
    }
}

@Composable
@Preview
fun CustomButton(
    text: String = "Button",
    onClick: () -> Unit = { },
    backgroundColor: Color = colorResource(id = R.color.primary),
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(20.dp),

        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        Text(text = text, color = colorResource(id = R.color.black))
    }
}

@Composable
fun ForgotPassword(navController: NavController) {

}

@Composable
fun SignUp(navController: NavController) {

}