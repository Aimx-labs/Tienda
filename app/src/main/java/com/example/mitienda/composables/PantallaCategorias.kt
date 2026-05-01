package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Usamos colores de la misma paleta Ethereal
val VerdeBotonFlotante = Color(0xFFC3D1C6) // Verde muy clarito para el botón con la flecha

@Composable
fun PantallaCategorias() {
    Scaffold(
        containerColor = FondoPerfil, // Mismo fondo de la app
        topBar = { TopBarCategoriasEthereal() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            BuscadorCategorias(modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(24.dp))
            FiltrosSuperiores()
            Spacer(modifier = Modifier.height(24.dp))
            ListaColecciones()
        }
    }
}

@Composable
fun TopBarCategoriasEthereal() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Buscar",
            tint = VerdeEthereal,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = "The Ethereal Atelier",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = VerdeEthereal
        )

        // Mini Avatar Placeholder
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
        )
    }
}

@Composable
fun BuscadorCategorias(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text("Search collections...", color = TextoSecundario) },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Buscar", tint = TextoSecundario)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp)), // Buscador muy redondeado como en el diseño
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun FiltrosSuperiores() {
    // Scroll horizontal para los filtros
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            BotonFiltro(texto = "Popularity", seleccionado = true)
        }
        item {
            BotonFiltro(texto = "Price", seleccionado = false)
        }
        item {
            BotonFiltro(texto = "Newest", seleccionado = false)
        }
    }
}

@Composable
fun BotonFiltro(texto: String, seleccionado: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (seleccionado) VerdeEthereal else Color.White)
            .clickable { /* Acción del filtro */ }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = texto,
            color = if (seleccionado) Color.White else TextoPrincipal,
            fontSize = 14.sp,
            fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
fun ListaColecciones() {
    // Usamos LazyColumn para la lista principal de colecciones
    LazyColumn(
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 100.dp), // Padding inferior para la nav bar
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TarjetaColeccionItem(
                titulo = "Fragrances",
                cantidad = "12 items",
                colorFondo = Color(0xFFC3B091) // Color crema/dorado oscuro
            )
        }
        item {
            TarjetaColeccionItem(
                titulo = "Jewelry",
                cantidad = "34 items",
                colorFondo = Color(0xFF4A5553) // Gris verdoso oscuro
            )
        }
        item {
            TarjetaColeccionItem(
                titulo = "Home Accents",
                cantidad = "58 items",
                colorFondo = Color(0xFFE4D5C7) // Beige claro
            )
        }
    }
}

@Composable
fun TarjetaColeccionItem(titulo: String, cantidad: String, colorFondo: Color) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorFondo) // Aquí iría la imagen real de la colección
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Text(
                    text = titulo,
                    color = if (colorFondo == Color(0xFFE4D5C7)) TextoPrincipal else Color.White, // Ajusta el color del texto si el fondo es claro
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = cantidad,
                    color = if (colorFondo == Color(0xFFE4D5C7)) TextoSecundario else Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Botón circular flotante con flecha
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(if (colorFondo == Color(0xFFE4D5C7)) VerdeEthereal else VerdeBotonFlotante)
                    .clickable { /* Acción ir a colección */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Ir a $titulo",
                    tint = if (colorFondo == Color(0xFFE4D5C7)) Color.White else VerdeEthereal,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaCategoriasPreview() {
    PantallaCategorias()
}