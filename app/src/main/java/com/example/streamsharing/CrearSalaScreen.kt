package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.streamsharing.ui.theme.PruebaComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearSalaScreen(navController: NavHostController) {
    var nombreSala by remember { mutableStateOf("") }
    var descripcionSala by remember { mutableStateOf("") }
    var esPublica by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crear sala",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Prepararemos tu grupo para que puedas mirar con amigos.")
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = nombreSala,
                onValueChange = { nombreSala = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = descripcionSala,
                onValueChange = { descripcionSala = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Implementación del Switch (Toggle) para Pública o Privada
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = if (esPublica) "Pública" else "Privada", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = esPublica,
                    onCheckedChange = { esPublica = it }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = { navController.popBackStack() }) {
                    Text(text = "Cancelar")
                }
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "Aceptar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrearSalaScreenPreview() {
    PruebaComposeTheme {
        CrearSalaScreen(navController = rememberNavController())
    }
}

