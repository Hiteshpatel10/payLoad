package com.geekaid.payload.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = lightColors(
    primary = Blue600,
    onPrimary = Color.Black,
    primaryVariant = Blue600,
    secondary = Color.White,
    secondaryVariant = Teal300,
    error = RedErrorDark,
    onError = RedErrorLight
)

private val LightColorPalette = lightColors(
    primary = Blue600,
    primaryVariant = Blue400,
    onPrimary = Color.Black,
    secondary = Color.White,
    secondaryVariant = Teal300,
    onSecondary = Color.Black,
    error = RedErrorDark,
    onError = RedErrorLight
)


@Composable
fun PayLoadTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}