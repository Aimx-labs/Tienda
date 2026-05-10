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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mitienda.composables.Data.Articulo
import java.util.Locale

val VerdeOscuro = Color(0xFF135041)
val FondoCantidad = Color(0xFFE8F0ED)

@Composable
fun TarjetaElemento(articulo: Articulo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

            AsyncImage(
                model = articulo.imagenUrl,
                contentDescription = "Imagen de ${articulo.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(12.dp)).background(Color.LightGray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = articulo.nombre,
                    fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black, maxLines = 2
                )

                Text(
                    text = "Size: ${articulo.talla} | Color: ${articulo.color}",
                    fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                Text(
                    text = String.format(Locale.US, "$%.2f", articulo.precio.toDouble()),
                    fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = VerdeOscuro
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.background(FondoCantidad, RoundedCornerShape(8.dp)).padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("-", color = VerdeOscuro, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp).clickable { })
                    Text("1", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("+", color = VerdeOscuro, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp).clickable { })
                }
            }

            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Delete, contentDescription = "Eliminar", tint = Color.LightGray)
            }
        }
    }
}