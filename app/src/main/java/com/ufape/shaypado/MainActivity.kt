package com.ufape.shaypado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.data.GlobalNavigationHandler
import com.ufape.shaypado.data.GlobalNavigator
import com.ufape.shaypado.ui.routes.Routes
import com.ufape.shaypado.ui.screens.login.AuthViewModel
import com.ufape.shaypado.ui.theme.ShaypadoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(), GlobalNavigationHandler {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        GlobalNavigator.registerHandler(this)

        super.onCreate(savedInstanceState)
        setContent {
            ShaypadoTheme {
                authViewModel.mockedLogin()
                // A surface container using the 'background' color from the theme
                Routes(authViewModel)
            }
        }
    }

    override fun sessionExpired() {
        authViewModel.sessionExpired()
    }
}