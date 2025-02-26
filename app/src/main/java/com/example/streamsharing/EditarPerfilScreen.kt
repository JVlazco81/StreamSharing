package com.example.streamsharing


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun EditarPerfilScreen(navController: NavHostController) {
    Scaffold(
        topBar = { EditarPerfilTopBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Botón para cambiar foto de perfil
            Button(onClick = { /* Acción para actualizar foto de perfil */ }) {
                Text(text = "Cambiar foto de perfil")
            }

            Spacer(modifier = Modifier.height(16.dp))

            InputField(label = "Username", placeholder = "[PLACEHOLDER]")
            InputField(label = "Email", placeholder = "[PLACEHOLDER]")
            InputField(label = "Descripción", placeholder = "[PLACEHOLDER]")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text("Editar perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()  }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        }
    )
}

@Composable
fun InputField(label: String, placeholder: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = placeholder,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun EditarPerfilScreenPreview() {
    PruebaComposeTheme {
        EditarPerfilScreen(navController = rememberNavController())
    }
}