package com.ufape.shaypado.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = LightTeal,
    secondary = Pink,
    background = Black,
    tertiary = DarkTeal,
    surface = DarkGrey,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkTeal,
    secondary = Pink,
    tertiaryContainer = Purple,
    background = White,
    tertiary = DarkTeal,
    surface =  Color(0xFFEEEEEE),
    onSurface = DarkGrey,
    onPrimaryContainer = White,
    primaryContainer = Teal,
    error = Red,
    surfaceVariant = LightTeal,
    secondaryContainer = Color(
        0xFFC3F1DA
    ),
    onSecondaryContainer = Color(
        0xFF295241
    ),
    onTertiaryContainer = Color(0xFFFFFFFF),
    inversePrimary = Color(0xFF65DBAD),
)

val textPrimary: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF212121) else Color(0xFF8A8A8A)

val textSecondary: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF5F5F5F) else Color(0xFFD3D3D3)


@Composable
fun ShaypadoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}