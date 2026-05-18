package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mitienda.composables.Data.ItemCarrito
import androidx.compose.material.icons.filled.Warning
import coil.compose.SubcomposeAsyncImage
import com.example.mitienda.composables.Data.Articulo
import com.example.mitienda.ui.theme.MiTiendaTheme
import java.util.Locale
@Composable
fun TarjetaElemento(
    item: ItemCarrito,
    onIncrementar: () -> Unit,
    onDecrementar: () -> Unit,
    onEliminar: () -> Unit
) {
    val articulo = item.articulo
    val VerdeOscuro = Color(0xFF135041)
    val FondoCantidad = Color(0xFFE8F0ED)

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

            SubcomposeAsyncImage(
                model = articulo.urlSegura,
                contentDescription = articulo.nombre,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(Modifier.fillMaxSize().background(Color(0xFFEBEBEB)), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = VerdeOscuro, strokeWidth = 2.dp, modifier = Modifier.size(24.dp))
                    }
                },
                error = {
                    Box(Modifier.fillMaxSize().background(Color(0xFFFFEBEE)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Warning, contentDescription = "Error", tint = Color.Red)
                    }
                },
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(articulo.nombre, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black, maxLines = 1)
                Text("Talla: ${articulo.tallaSegura} | Color: ${articulo.colorSeguro}", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(vertical = 4.dp))

                val precioTotalItem = articulo.precio * item.cantidad
                Text(String.format(Locale.US, "$%.2f", precioTotalItem), fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = VerdeOscuro)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.background(FondoCantidad, RoundedCornerShape(8.dp)).padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("-", color = VerdeOscuro, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp).clickable { onDecrementar() })
                    Text("${item.cantidad}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("+", color = VerdeOscuro, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp).clickable { onIncrementar() })
                }
            }

            IconButton(onClick = onEliminar) {
                Icon(Icons.Outlined.Delete, contentDescription = "Eliminar", tint = Color.LightGray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TarjetaElementoPreview() {
    MiTiendaTheme {
        TarjetaElemento(
            item = ItemCarrito(
                articulo = Articulo(
                    id = 1L,
                    nombre = "Camiseta de Algodón",
                    precio = 19.99f,
                    talla = "L",
                    color = "Azul"
                ),
                cantidad = 2
            ),
            onIncrementar = {},
            onDecrementar = {},
            onEliminar = {}
        )
    }
}