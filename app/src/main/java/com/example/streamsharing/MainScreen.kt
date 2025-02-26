package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.streamsharing.ui.theme.PruebaComposeTheme
import androidx.compose.foundation.clickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val tabTitles = listOf("Popular", "Acción", "Comedia", "Música")
    var selectedTab by remember { mutableStateOf(0) }

    // ✅ Define listas diferentes para cada categoría
    val roomsPopular = listOf(
        RoomData("The Prestige (2006)", "@pepitoconejo", 23, R.drawable.placeholder),
        RoomData("Megaproyectos chinos 2024", "@cartman2005", 64, R.drawable.placeholder),
    )

    val roomsAccion = listOf(
        RoomData("Mad Max: Fury Road", "@george_miller", 102, R.drawable.placeholder),
        RoomData("John Wick", "@babayaga", 85, R.drawable.placeholder),
        RoomData("Avengers: Endgame", "@marvel_fans", 220, R.drawable.placeholder),
    )

    val roomsComedia = listOf(
        RoomData("Superbad", "@mclovin", 45, R.drawable.placeholder),
        RoomData("The Hangover", "@wolfpack", 87, R.drawable.placeholder),
        RoomData("Dumb and Dumber", "@jim_carrey", 60, R.drawable.placeholder),
        RoomData("Step Brothers", "@ferrell", 35, R.drawable.placeholder),
    )

    val roomsMusica = listOf(
        RoomData("Bohemian Rhapsody", "@queenfans", 150, R.drawable.placeholder),
        RoomData("La La Land", "@musical_lover", 90, R.drawable.placeholder),
        RoomData("Whiplash", "@jazzdrummer", 55, R.drawable.placeholder),
        RoomData("A Star Is Born", "@ladygaga", 120, R.drawable.placeholder),
        RoomData("Rocketman", "@eltonjohn", 80, R.drawable.placeholder),
        RoomData("The Sound of Music", "@classicmusicals", 60, R.drawable.placeholder),
        RoomData("Mamma Mia!", "@abba", 110, R.drawable.placeholder),
        RoomData("School of Rock", "@jackblack", 75, R.drawable.placeholder),
        RoomData("Begin Again", "@indie_music", 65, R.drawable.placeholder),
        RoomData("Pitch Perfect", "@acapella", 95, R.drawable.placeholder),
        RoomData("The Blues Brothers", "@bluesfans", 50, R.drawable.placeholder),
        RoomData("Sing Street", "@80smusic", 70, R.drawable.placeholder),
        RoomData("Inside Llewyn Davis", "@folk_music", 40, R.drawable.placeholder),
        RoomData("Moana", "@disney_music", 130, R.drawable.placeholder),
        RoomData("Coco", "@pixar_music", 145, R.drawable.placeholder),
    )


    // ✅ Selecciona la lista correcta según la pestaña activa
    val currentRooms = when (selectedTab) {
        0 -> roomsPopular
        1 -> roomsAccion
        2 -> roomsComedia
        else -> roomsMusica
    }

    Scaffold(
        topBar = {
            Column {
                SearchAndActionsBar(navController)
                TabRow(selectedTabIndex = selectedTab) {
                    tabTitles.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("crearSalaScreen") },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Crear Sala")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            RoomList(currentRooms, navController) // ✅ Muestra la lista correspondiente
        }
    }
}

@Composable
fun SearchAndActionsBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar salas") },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { navController.navigate("agregarAmigosScreen") }) {
            Icon(painterResource(id = R.drawable.ic_add_friend), contentDescription = "Agregar amigos")
        }
        IconButton(onClick = { navController.navigate("notificacionesScreen") }) {
            Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notificaciones")
        }
        IconButton(onClick = { navController.navigate("perfilScreen/0") }) {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Perfil")
        }
    }
}

@Composable
fun RoomList(rooms: List<RoomData>, navController: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(rooms) { room ->
            RoomItem(room, navController)
        }
    }
}

@Composable
fun RoomItem(room: RoomData, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .clickable { navController.navigate("salaScreen") },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = room.imageRes),
            contentDescription = "Room Thumbnail",
            modifier = Modifier.size(80.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = room.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "Sala de ${room.creator}", fontSize = 12.sp)
            Text(text = "${room.viewers} personas viendo", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.DarkGray)
        }
    }
}

data class RoomData(val title: String, val creator: String, val viewers: Int, val imageRes: Int)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PruebaComposeTheme {
        MainScreen(navController = rememberNavController())
    }
}