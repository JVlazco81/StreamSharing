package com.example.streamsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

@Composable
fun AgregarAmigosScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AgregarAmigosTopBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Basado en interacciones recientes",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            RecentFriendsList()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarAmigosTopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text("Agregar amigos", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun RecentFriendsList() {
    Column {
        repeat(4) {
            FriendItem()
        }
    }
}

@Composable
fun FriendItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)), // ✅ Agrega fondo,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Avatar",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("[USERNAME]", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /* Enviar solicitud */ }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Enviar solicitud")
            }
            IconButton(onClick = { /* Eliminar usuario */ }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Eliminar usuario")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AgregarAmigosScreenPreview() {
    PruebaComposeTheme {
        AgregarAmigosScreen(navController = rememberNavController())
    }
}