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
import androidx.compose.foundation.lazy.items
import com.example.mitienda.composables.Data.CartManager
import java.util.Locale

val FondoGrisClaro = Color(0xFFF6F8F7)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompras(
    onIrAPagar: (Double) -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    val itemsCarrito = CartManager.listaCarrito
    val totalCalculado = itemsCarrito.sumOf { (it.articulo.precio * it.cantidad).toDouble() }

    Scaffold(
        containerColor = Color(0xFFF6F8F7),
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito", fontWeight = FontWeight.Bold, color = Color(0xFF135041)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF6F8F7))
            )
        },
        bottomBar = {
            if (itemsCarrito.isNotEmpty()) {
                Surface(modifier = Modifier.fillMaxWidth(), shadowElevation = 8.dp, color = Color.White) {
                    Button(
                        onClick = { onIrAPagar(totalCalculado) },
                        modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Pagar Total (${String.format(Locale.US, "$%.2f", totalCalculado)})", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        if (itemsCarrito.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Tu carrito está vacío", color = Color.Gray, fontSize = 16.sp)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding), contentPadding = PaddingValues(bottom = 100.dp)) {
                item {
                    Text("Revisa las cosas que quieras comprar.", color = Color.Gray, modifier = Modifier.padding(16.dp))
                }
                items(itemsCarrito, key = { it.articulo.id }) { item ->
                    TarjetaElemento(
                        item = item,
                        onIncrementar = { CartManager.agregarProducto(item.articulo) },
                        onDecrementar = { CartManager.decrementarProducto(item.articulo) },
                        onEliminar = { CartManager.eliminarProducto(item.articulo) }
                    )
                }
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