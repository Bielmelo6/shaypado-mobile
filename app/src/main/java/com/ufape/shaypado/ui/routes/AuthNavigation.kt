package com.ufape.shaypado.ui.routes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedCornerTextField() {
    var textState by remember { mutableStateOf("") }
    val maxLength = 110
    val blue = Color(0xff76a9ff)
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = { textState = it },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            if (textState.isNotEmpty()) {
                IconButton(onClick = { textState = "" }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
            }
        }
    )
    Text(
        text = "${textState.length} / $maxLength",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        textAlign = TextAlign.End,
        color = blue
    )
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
                Text(text = "fdsfd")
                RoundedCornerTextField()
                Button(onClick = {
                    navController.popBackStack()
                    navController.navigate(MobileNavigationScreen.NavRoot.route)
                }) {
                    Text(text = "Login")
                }
            }
        }
    }
}

@Composable
fun ForgotPassword(navController: NavController) {

}

@Composable
fun SignUp(navController: NavController) {

}