package com.ufape.shaypado.ui.screens.signUp

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.ButtonVariant
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen

@Composable
fun UserCreatedScreen(
    navController: NavController
) {
    val context = LocalContext.current

    fun goBack() {
        navController.navigate(AuthNavigationScreen.Login.route) {
            popUpTo(AuthNavigationScreen.NavRoot.route) {
                inclusive = true
            }
        }
    }

    val intent = Intent(Intent.ACTION_MAIN).apply {
        addCategory(Intent.CATEGORY_APP_EMAIL)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.shaypado_email),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            AppText(
                text = R.string.user_created_title,
                textType = TextType.TITLE_MEDIUM,
                fillWidth = true,
                textAlignment = TextAlign.Center
            )
        }



        AppButton(
            text = R.string.goback,
            onClick = { goBack() },
            variant = ButtonVariant.SECONDARY_CONTAINER
        )

        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = R.string.go_to_email,
            onClick = {
                startActivity(context, intent, null)
            },
        )
    }

}

@Preview
@Composable
fun UserCreatedScreenPreview() {
    UserCreatedScreen(navController = rememberNavController())
}