package com.ufape.shaypado.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.CustomButton
import com.ufape.shaypado.ui.components.CustomPasswordTextField
import com.ufape.shaypado.ui.components.CustomTextField
import com.ufape.shaypado.ui.routes.MobileNavigationScreen

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