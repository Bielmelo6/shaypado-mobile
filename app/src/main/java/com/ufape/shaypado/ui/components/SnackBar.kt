package com.ufape.shaypado.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun SnackBar(message: String? = null, reset: () -> Unit, content: @Composable () -> Unit) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (message != null) {
        LaunchedEffect(snackbarHostState) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short
                )
                reset()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier
                    .padding(WindowInsets.ime.asPaddingValues()),
                hostState = snackbarHostState
            )
        }
    ) { contentPadding ->
        Column (
            modifier = Modifier.fillMaxSize().padding(
                top = contentPadding.calculateTopPadding(),
            )
        ) {
            content()
        }
    }
}