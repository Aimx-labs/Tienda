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

// Colores del diseño
val VerdeOscuro = Color(0xFF135041)
val FondoCantidad = Color(0xFFE8F0ED)

@Composable
fun TarjetaElemento(articulo: Articulo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen cargada de Internet con Coil (Importada correctamente arriba)
            AsyncImage(
                model = articulo.imagenUrl,
                contentDescription = "Imagen de ${articulo.nombre}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop // Recorta la imagen para que encaje perfecto
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Contenidos (Textos y Controles)
            Column(modifier = Modifier.weight(1f)) {
                // Fila superior: Título y Bote de basura
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = articulo.nombre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Eliminar",
                        tint = Color.LightGray,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { /* TODO: Lógica para eliminar de la API */ }
                    )
                }

                // Subtítulo
                Text(
                    text = "Size: ${articulo.talla} | Color: ${articulo.color}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                )

                // Fila inferior: Cantidad y Precio
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Controles de Cantidad
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(FondoCantidad, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "—", color = VerdeOscuro, fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp).clickable { /* Restar */ }
                        )
                        Text(
                            text = "1", fontWeight = FontWeight.Bold, fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        Text(
                            text = "+", color = VerdeOscuro, fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp).clickable { /* Sumar */ }
                        )
                    }

                    // Precio
                    Text(
                        text = "€${articulo.precio}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp,
                        color = VerdeOscuro
                    )
                }
            }
        }
    }
}