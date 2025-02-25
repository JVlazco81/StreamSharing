package com.example.streamsharing.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🔹 Agregar la importación correcta de Typography
import androidx.compose.material3.Typography

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    onPrimary = Color.White,
    secondary = Color(0xFF03DAC6),
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black
)

// 🔹 Definir una instancia de Typography para evitar errores
private val DefaultTypography = Typography()

@Composable
fun PruebaComposeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = DefaultTypography, // 🔹 Usar la versión correcta
        content = content
    )
}