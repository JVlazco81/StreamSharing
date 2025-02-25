package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pruebacompose.R
import com.example.streamsharing.ui.theme.PruebaComposeTheme


@Composable
fun RegistroScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo), // Asegúrate de tener un logo en res/drawable
                contentDescription = "Logo de StreamSharing",
                modifier = Modifier.size(100.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Comparte con amigos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color =  MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = { navController.navigate("mainScreen") },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.google_logo), // Asegúrate de tener un logo de Google en res/drawable
                        contentDescription = "Logo de Google",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Continuar con Google")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = { navController.navigate("registroFormulario")},
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar con correo electrónico")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Al continuar, aceptas los términos de servicio y la política de privacidad de StreamSharing.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(onClick = { navController.navigate("loginFormulario") }) {
                Text(text = "¿Ya tienes cuenta? Inicia sesión", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    PruebaComposeTheme {
        RegistroScreen(navController = rememberNavController())
    }
}

