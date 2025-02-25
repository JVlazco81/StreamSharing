package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
fun ConfigSalaScreen(navController: NavHostController) {
    Scaffold(
        topBar = { ConfigSalaTopBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ConfigSection("Visibilidad") {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Visibilidad")
                    Spacer(modifier = Modifier.width(8.dp))
                    var expanded by remember { mutableStateOf(false) }
                    var selectedVisibility by remember { mutableStateOf("Pública") }

                    Box {
                        Button(onClick = { expanded = true }) {
                            Text(selectedVisibility)
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Pública") },
                                onClick = {
                                    selectedVisibility = "Pública"
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Privada") },
                                onClick = {
                                    selectedVisibility = "Privada"
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            ConfigSection("Aprobación") {
                ConfigToggleItem("Aprobación de participantes manual.", Icons.Default.Person, true)
                ConfigToggleItem("Cerrar acceso a la sala.", Icons.Default.DoorFront, true)
            }

            ConfigSection("Solo invitados") {
                ConfigToggleItem("Solo invitados", Icons.Default.Group, true)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigSalaTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text("Configuración", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ConfigSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
fun ConfigToggleItem(label: String, icon: ImageVector, defaultState: Boolean) {
    var toggled by remember { mutableStateOf(defaultState) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = label)
        Spacer(modifier = Modifier.width(8.dp))
        Text(label)
        Spacer(modifier = Modifier.weight(1f))
        Switch(checked = toggled, onCheckedChange = { toggled = it })
    }
}

@Preview(showBackground = true)
@Composable
fun ConfigSalaScreenPreview() {
    PruebaComposeTheme {
        ConfigSalaScreen(navController = rememberNavController())
    }
}