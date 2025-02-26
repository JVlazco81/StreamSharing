package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
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
import com.example.streamsharing.ui.theme.PruebaComposeTheme

@Composable
fun PerfilScreen(navController: NavHostController, startTabIndex: Int = 0) {
    var selectedTabIndex by remember { mutableStateOf(startTabIndex) } // ✅ Usa el argumento para inicializar el índice

    Scaffold(
        topBar = { PerfilTopBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            PerfilInfo()
            Spacer(modifier = Modifier.height(16.dp))
            EstadisticasUsuario()
            Spacer(modifier = Modifier.height(16.dp))
            PerfilTabs(selectedTabIndex) { newIndex ->
                selectedTabIndex = newIndex
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text("Perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("loginFormulario") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            } }) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Cerrar sesion")
            }
            IconButton(onClick = { navController.navigate("editarPerfilScreen") }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar perfil")
            }
        }
    )
}

@Composable
fun PerfilInfo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Avatar",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("[USERNAME]", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Miembro desde [FECHA]", fontSize = 14.sp)
            Text("StreamSharing.tv/[USERNAME]", fontSize = 14.sp)
            Text("[Descripcion]", fontSize = 14.sp)
        }
    }
}

@Composable
fun EstadisticasUsuario() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("#", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Amigos", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("#", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Personas alcanzadas", fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("#", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Horas transmitidas", fontSize = 14.sp)
        }
    }
}

@Composable
fun PerfilTabs(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Historial", "Lista de amigos")

    Column {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = { Text(title, fontWeight = FontWeight.Bold) }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> HistorialLista()
            1 -> ListaAmigos()
        }
    }
}

@Composable
fun HistorialLista() {
    LazyColumn {
        items(3) {
            SalaItem()
        }
    }
}

@Composable
fun SalaItem() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Sala",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("[NOMBRE DE LA SALA]", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("[CREADOR DE LA SALA]", fontSize = 14.sp)
            Text("[FECHA DE LA SESION]", fontSize = 12.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaAmigos() {
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedUser by remember { mutableStateOf("[amigo]") }

    Column {
        LazyColumn {
            items(7) { amigo ->
                AmigoItem(
                    username = "[amigo $amigo]",
                    onMoreClick = {
                        selectedUser = "[amigo $amigo]"
                        showBottomSheet = true
                    }
                )
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                BottomSheetContent(username = selectedUser, onDismiss = { showBottomSheet = false })
            }
        }
    }
}

@Composable
fun AmigoItem(username: String, onMoreClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Amigo",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(username, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onMoreClick) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Opciones")
        }
    }
}

@Composable
fun BottomSheetContent(username: String, onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Opciones para $username", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Ver perfil */ onDismiss() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver perfil de $username")
        }

        Button(
            onClick = { /* Eliminar amigo */ onDismiss() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Eliminar a $username")
        }

        Button(
            onClick = { /* Invitar a sala */ onDismiss() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Invitar a sala $username")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
            Text("Cerrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PerfilScreenPreview() {
    PruebaComposeTheme {
        PerfilScreen(navController = rememberNavController())
    }
}