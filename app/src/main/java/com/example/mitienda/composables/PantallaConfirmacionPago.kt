package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaConfirmacionPago(onNavigateHome: () -> Unit = {}) {
    Scaffold(
        containerColor = Color(0xFFF4F6F4)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text("Review Your Order", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(24.dp))

            // Resumen (estilo la imagen)
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Summary", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        Text("Subtotal", color = Color.Gray)
                        Text("$257.00")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        Text("Standard Shipping", color = Color.Gray)
                        Text("$12.00")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        Text("Estimated Tax", color = Color.Gray)
                        Text("$21.50")
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Total", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("$290.50", fontWeight = FontWeight.ExtraBold, color = Color(0xFF135041), fontSize = 22.sp)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Banner de recompensas
                    Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0xFFE2F3ED)).padding(16.dp)) {
                        Text("Ethereal Rewards: You'll earn 290 points with this purchase.", fontSize = 12.sp, color = Color(0xFF135041))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onNavigateHome,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Place Your Order", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}