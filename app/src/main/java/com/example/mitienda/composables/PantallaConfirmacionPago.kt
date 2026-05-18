package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.mitienda.composables.Data.CartManager
import java.util.Locale

@Composable
fun PantallaConfirmacionPago(
    subtotal: Double,
    onNavigateHome: () -> Unit = {}
) {
    val envio = if (subtotal > 0) 12.00 else 0.00
    val impuestos = subtotal * 0.08
    val total = subtotal + envio + impuestos
    val puntos = total.toInt()

    Scaffold(containerColor = Color(0xFFF4F6F4)) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState()).padding(24.dp)
        ) {
            Text("Revisa Tu Pedido", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Resumen", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        Text("Subtotal", color = Color.DarkGray)
                        Text(String.format(Locale.US, "$%.2f", subtotal), color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        Text("Envío Estándar", color = Color.DarkGray)
                        Text(String.format(Locale.US, "$%.2f", envio), color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                        Text("Impuestos Estimados", color = Color.DarkGray)
                        Text(String.format(Locale.US, "$%.2f", impuestos), color = Color.Black)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text("Total", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                        Text(String.format(Locale.US, "$%.2f", total), fontWeight = FontWeight.ExtraBold, color = Color(0xFF135041), fontSize = 22.sp)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0xFFE2F3ED)).padding(16.dp)) {
                        Text("The Aimox Store Rewards: Ganarás $puntos puntos.", fontSize = 12.sp, color = Color(0xFF135041))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            CartManager.listaCarrito.clear() // <-- ¡Vaciamos el carrito!
                            onNavigateHome()
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Realizar Pago", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaConfirmacionPagoPreview() {
    PantallaConfirmacionPago(subtotal = 1500.0)
}