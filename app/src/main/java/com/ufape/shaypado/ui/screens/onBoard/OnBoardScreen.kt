package com.ufape.shaypado.ui.screens.onBoard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppText
import com.ufape.shaypado.ui.components.AppButton
import com.ufape.shaypado.ui.components.TextType
import com.ufape.shaypado.ui.model.UserType
import com.ufape.shaypado.ui.routes.AuthNavigationScreen
import com.ufape.shaypado.ui.screens.login.AuthViewModel
import com.ufape.shaypado.ui.theme.ShaypadoImage
import com.ufape.shaypado.ui.theme.ShaypadoPetImage
import com.ufape.shaypado.ui.theme.White

@Composable
@Preview
fun OnBoardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(10.dp))

        ShaypadoPetImage()

        ShaypadoImage(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onPrimary,
        )

        Spacer(modifier = Modifier.height(50.dp))

        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = White
        )
    }
}