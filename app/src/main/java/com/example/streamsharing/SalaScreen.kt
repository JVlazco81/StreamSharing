package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.streamsharing.ui.theme.PruebaComposeTheme

@Composable
fun SalaScreen(navController: NavHostController) {
    Scaffold(
        topBar = { SalaScreenTopBar(navController) },
        bottomBar = { ChatInputField() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            VideoPlayerPlaceholder()
            VideoControls()
            ChatSection()
        }
    }
}

@Composable
fun SalaScreenTopBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Icon(imageVector = Icons.Default.Lock, contentDescription = "Privada")
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Sala mixta",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { navController.navigate("perfilScreen/1") }) {
            Icon(imageVector = Icons.Outlined.PersonAdd, contentDescription = "Agregar amigos")
        }
        IconButton(onClick = { navController.navigate("configSalaScreen") }) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Ajustes")
        }
    }
}

@Composable
fun VideoPlayerPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Reproducir", tint = Color.White, modifier = Modifier.size(50.dp))
    }
}

@Composable
fun VideoControls() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ControlButton(Icons.Default.Close, "Terminar")
        ControlButton(Icons.Default.SettingsRemote, "Control")
        ControlButton(Icons.Default.VolumeOff, "Mutear")
        ControlButton(Icons.Default.HighQuality, "Calidad")
        ControlButton(Icons.Default.ScreenRotation, "Rotar")
    }
}

@Composable
fun ControlButton(icon: ImageVector, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = { /* Acción */ }) {
            Icon(imageVector = icon, contentDescription = text)
        }
        Text(text, fontSize = 12.sp)
    }
}

@Composable
fun ChatSection() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Chat de la sala", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        ChatMessage("USUARIO1", "Lorem ipsum dolor sit amet")
        ChatMessage("USUARIO2", "Lorem ipsum dolor sit amet")
        ChatMessage("Me", "Hola")
    }
}

@Composable
fun ChatMessage(username: String, message: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Usuario", modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = username, fontWeight = FontWeight.Bold)
            Text(text = message)
        }
    }
}

@Composable
fun ChatInputField() {
    var message by remember { mutableStateOf("") } // Guarda el texto ingresado por el usuario

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = message, // Se usa el estado para almacenar el valor
            onValueChange = { message = it }, // Actualiza el estado al escribir
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (message.isEmpty()) { // Placeholder si está vacío
                        Text("Escribe un mensaje...", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )
        IconButton(onClick = { /* Acción de mutear */ }) {
            Icon(imageVector = Icons.Default.MicOff, contentDescription = "Mute")
        }
        IconButton(onClick = { /* Acción de cámara */ }) {
            Icon(imageVector = Icons.Default.VideocamOff, contentDescription = "Cámara")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SalaScreenPreview() {
    PruebaComposeTheme {
        SalaScreen(navController = rememberNavController())
    }
}