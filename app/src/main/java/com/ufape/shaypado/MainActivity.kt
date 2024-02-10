package com.ufape.shaypado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.ui.routes.Routes
import com.ufape.shaypado.ui.theme.ShaypadoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShaypadoTheme {
                // A surface container using the 'background' color from the theme
                Routes(navController = rememberNavController())
            }
        }
    }
}