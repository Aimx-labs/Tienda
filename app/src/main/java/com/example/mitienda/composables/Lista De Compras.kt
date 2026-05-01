package com.example.mitienda.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mitienda.composables.Data.Articulo
import com.example.mitienda.network.RetrofitProductos

val FondoGrisClaro = Color(0xFFF6F8F7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompras(){
    var articulos by remember { mutableStateOf<List<Articulo>>(emptyList()) }
    var mostrarDialogo by remember { mutableStateOf(false) }
    var textoTemporal by remember { mutableStateOf("") }

    // Llamada a tu API real
    LaunchedEffect(Unit){
        articulos = RetrofitProductos.apiArticulosService.obtenerArticulos()
    }

    if(mostrarDialogo){
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text("Agregar Articulo") },
            text = {
                TextField(
                    value = textoTemporal,
                    onValueChange = { textoTemporal = it }
                )
            },
            confirmButton = {
                Button(onClick = {
                    // TODO: Lógica para hacer POST a tu API
                    mostrarDialogo = false
                    textoTemporal = ""
                }){
                    Text("Aceptar")
                }
            }
        )
    }

    Scaffold(
        containerColor = FondoGrisClaro, // Fondo claro para que las tarjetas blancas resalten
        topBar = {
            TopAppBar(
                title = { Text("Carrito de Compras", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoGrisClaro)
            )
        },
        bottomBar = {
            CartBottomNavigation() // Llamamos a tu barra de navegación actualizada
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { mostrarDialogo = true },
                containerColor = VerdeOscuro,
                contentColor = Color.White
            ){
                Icon(Icons.Default.Add, contentDescription = "Agregar Articulos")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = padding){
            items(count = articulos.size){ index ->
                TarjetaElemento(articulos[index]) // Usamos el nuevo diseño
            }
        }
    }
}

// Nueva Barra de Navegación Inferior
@Composable
fun CartBottomNavigation() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Principal") },
            label = { Text("Home") },
            selected = false,
            onClick = { /* Navegar a Principal */ },
            colors = NavigationBarItemDefaults.colors(unselectedIconColor = Color.Gray)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Categorías") },
            label = { Text("Categories") },
            selected = false,
            onClick = { /* Navegar a Categorías */ },
            colors = NavigationBarItemDefaults.colors(unselectedIconColor = Color.Gray)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Cart") },
            selected = true, // Marcado como activo
            onClick = { /* Ya estamos en el carrito */ },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = VerdeOscuro,
                selectedIconColor = Color.White,
                selectedTextColor = VerdeOscuro
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Profile") },
            selected = false,
            onClick = { /* Navegar a PantallaCrearPerfil() */ },
            colors = NavigationBarItemDefaults.colors(unselectedIconColor = Color.Gray)
        )
    }
}