package com.example.mitienda.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mitienda.composables.Data.Articulo
import com.example.mitienda.network.RetrofitProductos

val FondoGrisClaro = Color(0xFFF6F8F7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompras(onIrAPagar: () -> Unit = {}) {
    var articulos by remember { mutableStateOf<List<Articulo>>(emptyList()) }

    LaunchedEffect(Unit) {
        articulos = RetrofitProductos.apiArticulosService.obtenerArticulos()
    }

    Scaffold(
        containerColor = FondoGrisClaro,
        topBar = {
            TopAppBar(
                title = { Text("Cart", fontWeight = FontWeight.ExtraBold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoGrisClaro)
            )
        },
        bottomBar = {
            if (articulos.isNotEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 8.dp,
                    color = Color.White
                ) {
                    Button(
                        onClick = onIrAPagar,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Checkout (€${articulos.sumOf { it.precio.toDouble() }})", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = padding
        ) {
            item {
                Text(
                    text = "Review your curated selection of atelier pieces.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(count = articulos.size) { index ->
                TarjetaElemento(articulos[index])
            }

            item { Spacer(modifier = Modifier.height(80.dp)) } // Espacio para que el botón no tape la última tarjeta
        }
    }
}