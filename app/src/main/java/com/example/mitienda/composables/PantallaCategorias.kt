package com.example.mitienda.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mitienda.R

@Composable
fun PantallaCategorias(
    onProductoClick: () -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = FondoPerfil,
        topBar = { TopBarCategoriasEthereal(onPerfilClick = onPerfilClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            BuscadorCategorias(modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(24.dp))
            FiltrosSuperiores()
            Spacer(modifier = Modifier.height(24.dp))
            ListaColecciones(onProductoClick = onProductoClick)
        }
    }
}

@Composable
fun TopBarCategoriasEthereal(onPerfilClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = VerdeEthereal, modifier = Modifier.size(24.dp))
        Text("The Aimox Store", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = VerdeEthereal)

        Image(
            painter = painterResource(id = R.drawable.amongus),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { onPerfilClick() }
        )
    }
}

@Composable
fun BuscadorCategorias(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text("Buscar", color = TextoSecundario) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar", tint = TextoSecundario) },
        modifier = modifier.fillMaxWidth().height(56.dp).clip(RoundedCornerShape(28.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White, focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent, disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun FiltrosSuperiores() {
    LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        item { BotonFiltro(texto = "Popular", seleccionado = true) }
        item { BotonFiltro(texto = "Por Precio", seleccionado = false) }
        item { BotonFiltro(texto = "Mas Nuevo", seleccionado = false) }
    }
}

@Composable
fun BotonFiltro(texto: String, seleccionado: Boolean) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(if (seleccionado) VerdeEthereal else Color.White).clickable { }.padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(texto, color = if (seleccionado) Color.White else TextoPrincipal, fontSize = 14.sp, fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Medium)
    }
}

@Composable
fun ListaColecciones(onProductoClick: () -> Unit = {}) {
    LazyColumn(contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 100.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { TarjetaColeccionItem("Accesorios", "12 items", "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcQrM7n9xKNrpQJLDHswR5VMPL5tCbzjAOcYrOtdgO9AtXBlxGaFkjJrJVdQbVwYGKIbFmMhTwqO09pkU_b-ILHxtMhG7WQcdBzxvhRrzWGoCZXKiGshJo9vWvGxhyW3IBxHR2p6WEFIStc&usqp=CAc", onClick = onProductoClick) }
        item { TarjetaColeccionItem("Telefonos", "34 items", "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?q=80&w=500&auto=format&fit=crop", onClick = onProductoClick) }
        item { TarjetaColeccionItem("Laptops y varios", "58 items", "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?q=80&w=500&auto=format&fit=crop", onClick = onProductoClick) }
    }
}

@Composable
fun TarjetaColeccionItem(titulo: String, cantidad: String, imageUrl: String, onClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth().height(180.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            AsyncImage(
                model = imageUrl,
                contentDescription = titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)))

            Column(modifier = Modifier.align(Alignment.BottomStart).padding(20.dp)) {
                Text(titulo, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                Text(cantidad, color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }

            Box(
                modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp).size(48.dp).clip(CircleShape).background(VerdeEthereal).clickable {  },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Ir a $titulo", tint = Color.White, modifier = Modifier.size(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaCategoriasPreview() {
    PantallaCategorias()
}