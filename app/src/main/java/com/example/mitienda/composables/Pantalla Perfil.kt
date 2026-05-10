package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colores basados en tu diseño "The Ethereal Atelier"
val FondoPerfil = Color(0xFFF4F6F4)
val VerdeEthereal = Color(0xFF005C4A)
val GrisFondoTarjeta = Color(0xFFFFFFFF)
val TextoPrincipal = Color(0xFF1A1A1A)
val TextoSecundario = Color(0xFF757575)
val RojoAlerta = Color(0xFFB3261E)

@Composable
fun PantallaPerfil() {
    Scaffold(
        containerColor = FondoPerfil,
        topBar = { TopBarPerfilEthereal() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            CabeceraPerfil()
            Spacer(modifier = Modifier.height(32.dp))
            SeccionMisPedidos()
            Spacer(modifier = Modifier.height(24.dp))
            MenuOpciones()
            Spacer(modifier = Modifier.height(40.dp))
            BotonCerrarSesion()
            Spacer(modifier = Modifier.height(100.dp)) // Espacio para el Bottom Navigation
        }
    }
}

@Composable
fun TopBarPerfilEthereal() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Buscar",
            tint = VerdeEthereal,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = "The Aimox Store",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = VerdeEthereal
        )

        // Mini Avatar Placeholder
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.DarkGray)
        )
    }
}

@Composable
fun CabeceraPerfil() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            // Avatar Circular Grande
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .border(3.dp, VerdeEthereal, CircleShape)
            )
            // Badge de Nivel (GOLD)
            Box(
                modifier = Modifier
                    .offset(y = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VerdeEthereal)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "GOLD",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Jonathan Alberto",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = TextoPrincipal
        )

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 4.dp)) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = VerdeEthereal,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Miembro Gold",
                fontSize = 14.sp,
                color = VerdeEthereal,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun SeccionMisPedidos() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mis Pedidos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
            Text(
                text = "Ver todos",
                fontSize = 14.sp,
                color = VerdeEthereal,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { /* Acción ver todos */ }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = GrisFondoTarjeta),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Imagen del pedido (Placeholder)
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF2C3E35))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "PEDIDO #8821",
                            fontSize = 12.sp,
                            color = TextoSecundario,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFDCE8E2))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "En camino",
                                fontSize = 10.sp,
                                color = VerdeEthereal,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Alienware 17 R4",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextoPrincipal
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Entrega estimada:\nMañana, 14:00",
                        fontSize = 12.sp,
                        color = TextoSecundario
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Detalles",
                    tint = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun MenuOpciones() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ItemMenuEthereal(
            icono = Icons.Default.LocationOn,
            titulo = "Direcciones de Envío",
            subtitulo = "2 direcciones guardadas"
        )
        ItemMenuEthereal(
            icono = Icons.Default.ShoppingCart, // Placeholder para tarjeta
            titulo = "Métodos de Pago",
            subtitulo = "Visa terminada en **** 4492"
        )
        ItemMenuEthereal(
            icono = Icons.Default.Notifications,
            titulo = "Notificaciones",
            subtitulo = "Ofertas y estado de pedidos",
            tieneNotificacion = true
        )
        ItemMenuEthereal(
            icono = Icons.Default.Face, // Placeholder para soporte
            titulo = "Soporte",
            subtitulo = "Ayuda, FAQ y contacto directo"
        )
    }
}

@Composable
fun ItemMenuEthereal(
    icono: ImageVector,
    titulo: String,
    subtitulo: String,
    tieneNotificacion: Boolean = false
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GrisFondoTarjeta),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Acción del menú */ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF4F6F4)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icono, contentDescription = null, tint = VerdeEthereal, modifier = Modifier.size(20.dp))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = titulo, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextoPrincipal)
                Text(text = subtitulo, fontSize = 12.sp, color = TextoSecundario)
            }

            if (tieneNotificacion) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(RojoAlerta))
                Spacer(modifier = Modifier.width(8.dp))
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun BotonCerrarSesion() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { /* Lógica de cerrar sesión */ }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Cerrar Sesión",
            tint = RojoAlerta
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Cerrar Sesión",
            color = RojoAlerta,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPerfilPreview() {
    PantallaPerfil()
}