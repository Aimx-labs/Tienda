package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mitienda.ui.theme.MiTiendaTheme

@Composable
fun PantallaVender(onPublicacionExitosa: () -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp)) {
        Text("Publicar Artículo", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF135041))
        Text("Configura las imágenes y detalles de tu producto.", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier.fillMaxWidth().height(160.dp).clip(RoundedCornerShape(16.dp)).background(Color(0xFFEBEBEB)),
            contentAlignment = Alignment.Center
        ) {
            if (imagenUrl.isNotEmpty()) {
                AsyncImage(model = imagenUrl, contentDescription = "Previsualización", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = Color.Gray)
                    Text("Ingresa una URL de imagen abajo para ver la previa", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = titulo, onValueChange = { titulo = it },
            label = { Text("Título del producto") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precio, onValueChange = { precio = it },
            label = { Text("Precio ($)") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = imagenUrl, onValueChange = { imagenUrl = it },
            label = { Text("Enlace / URL de la imagen") }, placeholder = { Text("https://ejemplo.com/imagen.jpg") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descripcion, onValueChange = { descripcion = it },
            label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth().height(120.dp), shape = RoundedCornerShape(12.dp), maxLines = 4
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { if (titulo.isNotEmpty() && precio.isNotEmpty()) onPublicacionExitosa() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Publicar Ahora", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaVenderPreview() {
    MiTiendaTheme {
        PantallaVender(onPublicacionExitosa = {})
    }
}