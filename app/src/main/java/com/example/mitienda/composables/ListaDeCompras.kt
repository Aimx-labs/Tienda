package com.example.mitienda.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.mitienda.R
import com.example.mitienda.composables.Data.Articulo
import com.example.mitienda.network.RetrofitProductos
import java.util.Locale

val FondoGrisClaro = Color(0xFFF6F8F7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompras(
    onIrAPagar: (Double) -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    var articulos by remember { mutableStateOf<List<Articulo>>(emptyList()) }

    LaunchedEffect(Unit) {
        articulos = RetrofitProductos.apiArticulosService.obtenerArticulos()
    }

    Scaffold(
        containerColor = FondoGrisClaro,
        topBar = { TopBarCarritoAimox(onPerfilClick = onPerfilClick) },
        bottomBar = {
            if (articulos.isNotEmpty()) {
                val total = articulos.sumOf { it.precio.toDouble() }
                val totalFormateado = String.format(Locale.US, "$%.2f", total)

                Surface(modifier = Modifier.fillMaxWidth(), shadowElevation = 8.dp, color = Color.White) {
                    Button(
                        onClick = { onIrAPagar(total) },
                        modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Total ($totalFormateado)", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item {
                Text(
                    text = "Revisa las cosas que quieras comprar.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(count = articulos.size) { index ->
                TarjetaElemento(articulos[index])
            }
        }
    }
}

@Composable
fun TopBarCarritoAimox(onPerfilClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Carrito", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp, color = Color.Black)
        Image(
            painter = painterResource(id = R.drawable.amongus), // Cambia por tu avatar
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(32.dp).clip(CircleShape).background(Color.LightGray).clickable { onPerfilClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListaComprasPreview() {
    ListaCompras()
}