package com.ufape.shaypado.ui.screens.onBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.FullWidthButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.theme.White

@Composable
fun OnBoardScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .padding(top = 175.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            AppText(
                textType = TextType.TITLE_LARGE,
                text = "Bem-vindo !!",
                color = White,
                size = 59.sp,
            )
            AppText(
                textType = TextType.TITLE_MEDIUM,
                textAlignment = TextAlign.Center,
                text = "Uma experiência única na forma de \n praticar exercício físico",
                color = White,
            )

            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = R.drawable.brand_logo),
                contentDescription = "Shaypado"
            )

            Spacer(modifier = Modifier.height(147.dp))

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
                Spacer(modifier = Modifier.height(24.dp))

                FullWidthButton(
                    backgroundColor = MaterialTheme.colorScheme.secondary,
                    text = stringResource(id = R.string.button_login),
                    onClick = {
                        navController.navigate(AuthNavigationScreen.Login.route)
                    }
                )

                Spacer(modifier = Modifier.height(36.dp))
                AppText(
                    textType = TextType.LABEL_MEDIUM,
                    text = "Ainda não possui conta no Shaypado?",
                    textAlignment = TextAlign.Center,
                    fillWidth = true
                )
                AppText(
                    textType = TextType.LABEL_MEDIUM,
                    text = "Cadastre-se aqui!",
                    textAlignment = TextAlign.Center,
                    fillWidth = true,
                    color = MaterialTheme.colorScheme.primary,
                    onPress = {
                        navController.navigate(AuthNavigationScreen.SignUp.route)
                    }
                )
            }
        }
    }
}