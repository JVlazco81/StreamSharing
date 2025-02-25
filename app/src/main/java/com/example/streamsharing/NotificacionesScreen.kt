package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.streamsharing.ui.theme.PruebaComposeTheme

@Composable
fun NotificacionesScreen(navController: NavHostController) {
    Scaffold(
        topBar = { NotificacionesTopBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn {
                items(getNotificaciones()) { notificacion ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant) // ✅ Fondo para cada item
                            .padding(8.dp)
                    ) {
                        NotificacionItem(notificacion)
                    }
                    Spacer(modifier = Modifier.height(10.dp)) // Espaciado entre elementos
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificacionesTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text("Notificaciones", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NotificacionItem(notificacion: Notificacion) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = notificacion.icono, contentDescription = "Icono de notificación")
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "${notificacion.usuario} ${notificacion.mensaje}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = notificacion.fecha, fontSize = 12.sp)
        }
        IconButton(onClick = { /* Acción para eliminar notificación */ }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Eliminar notificación")
        }
    }
}

data class Notificacion(
    val usuario: String,
    val mensaje: String,
    val fecha: String,
    val icono: androidx.compose.ui.graphics.vector.ImageVector
)

fun getNotificaciones(): List<Notificacion> {
    return listOf(
        Notificacion("[USERNAME]", "te invitó a una sala", "[FECHA]", Icons.Default.Email),
        Notificacion("[USERNAME]", "te envió solicitud", "[FECHA]", Icons.Default.PersonAdd),
        Notificacion("[USERNAME]", "te invitó a una sala", "[FECHA]", Icons.Default.Email),
        Notificacion("[USERNAME]", "te envió solicitud", "[FECHA]", Icons.Default.PersonAdd)
    )
}

@Preview(showBackground = true)
@Composable
fun NotificacionesScreenPreview() {
    PruebaComposeTheme {
        NotificacionesScreen(navController = rememberNavController())
    }
}