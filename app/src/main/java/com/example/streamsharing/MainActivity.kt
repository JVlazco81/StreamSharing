package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.streamsharing.ui.theme.PruebaComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaComposeTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "registroScreen"
    ) {
        composable("registroScreen") {
            RegistroScreen(navController) // <- Pasa el controlador
        }
        composable("registroFormulario") {
            RegistroFormulario(navController) // <- Pasa el controlador si es necesario
        }
        composable("crearUsernameScreen") {
            CrearUsernameScreen(navController)
        }
        composable("agregarAmigosScreen") {
            AgregarAmigosScreen(navController)
        }
        composable("configSalaScreen"){
            ConfigSalaScreen(navController)
        }
        composable("crearSalaScreen"){
            CrearSalaScreen(navController)
        }
        composable("crearUsernameScreen"){
            CrearUsernameScreen(navController)
        }
        composable("editarPerfilScreen"){
            EditarPerfilScreen(navController)
        }
        composable("loginFormulario"){
            LoginFormulario(navController)
        }
        composable("mainScreen"){
            MainScreen(navController)
        }
        composable("notificacionesScreen"){
            NotificacionesScreen(navController)
        }
        composable("perfilScreen/{startTabIndex}") { backStackEntry ->
            val startTabIndex = backStackEntry.arguments?.getString("startTabIndex")?.toIntOrNull() ?: 0
            PerfilScreen(navController, startTabIndex)
        }
        composable("salaScreen"){
            SalaScreen(navController)
        }
    }
}