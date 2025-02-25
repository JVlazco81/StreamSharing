package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.streamsharing.ui.theme.PruebaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaComposeTheme {
                RegistroScreen() // Llamamos la pantalla de registro
            }
        }
    }
}