package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaDetallesProducto(onBack: () -> Unit = {}) {
    Scaffold(
        containerColor = Color(0xFFF4F6F4),
        bottomBar = { BarraInferiorDetalles() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar Custom
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack, modifier = Modifier.background(Color.White, CircleShape).size(40.dp)) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = Color(0xFF135041))
                }
                Text("The Ethereal Atelier", fontWeight = FontWeight.Bold, color = Color(0xFF135041))
                IconButton(onClick = { }, modifier = Modifier.background(Color.White, CircleShape).size(40.dp)) {
                    Icon(Icons.Default.Share, contentDescription = "Compartir", tint = Color(0xFF135041))
                }
            }

            // Imagen Principal
            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(350.dp)
                    .clip(RoundedCornerShape(24.dp)).background(Color(0xFFE2E6E3))
            ) {
                Box(modifier = Modifier.padding(16.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFFA6E5D6))) {
                    Text("LIMITED EDITION", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF135041), modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info del Producto
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) { Icon(Icons.Default.Star, null, tint = Color(0xFF135041), modifier = Modifier.size(14.dp)) }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("4.8 (124 reviews)", color = Color.Gray, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Verdant Essence No. 4", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text("$185.00", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF135041))

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "A botanical symphony captured in glass. Verdant Essence No. 4 is a hand-distilled fragrance featuring top notes of wild bergamot, a heart of crushed moss, and a base of ancient cedarwood.",
                    color = Color.DarkGray, lineHeight = 22.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun BarraInferiorDetalles() {
    Surface(color = Color.White, shadowElevation = 16.dp) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp).navigationBarsPadding(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { },
                modifier = Modifier.weight(1f).height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Add to Cart", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Box(
                modifier = Modifier.size(56.dp).clip(RoundedCornerShape(16.dp)).background(Color(0xFFE2E6E3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorito", tint = Color(0xFF135041))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaDetallesProductoPreview() {
    PantallaDetallesProducto()
}