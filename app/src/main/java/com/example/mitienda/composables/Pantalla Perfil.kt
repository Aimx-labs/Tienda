package com.example.mitienda.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mitienda.R

val FondoPerfil = Color(0xFFF4F6F4)
val GrisFondoTarjeta = Color(0xFFFFFFFF)
val RojoAlerta = Color(0xFFB3261E)
val VerdeEtherealPerfil = Color(0xFF135041) // Renombrado para no chocar
val TextoPrincipalPerfil = Color(0xFF1C1B1F)
val TextoSecundarioPerfil = Color(0xFF757575)

@Composable
fun PantallaPerfil(
    nombreUsuario: String = "Jonathan Alberto",
    imagenPerfil: Int = R.drawable.amongus,
    onCerrarSesion: () -> Unit = {},
    onIrAVender: () -> Unit = {} // <-- Acción recibida
) {
    Scaffold(
        containerColor = FondoPerfil,
        topBar = { TopBarPerfilEthereal(imagenPerfil) }
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
            CabeceraPerfil(nombreUsuario, imagenPerfil)
            Spacer(modifier = Modifier.height(32.dp))
            SeccionMisPedidos()
            Spacer(modifier = Modifier.height(24.dp))
            MenuOpciones(onIrAVender) // Pasamos la acción al menú
            Spacer(modifier = Modifier.height(40.dp))
            BotonCerrarSesion(onClick = onCerrarSesion)
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun TopBarPerfilEthereal(imagenPerfil: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = VerdeEtherealPerfil, modifier = Modifier.size(24.dp))
        Text("The Aimox Store", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = VerdeEtherealPerfil)
        AsyncImage(
            model = imagenPerfil,
            contentDescription = "Mini Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun CabeceraPerfil(nombre: String, foto: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = foto,
                contentDescription = "Foto",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(3.dp, VerdeEtherealPerfil, CircleShape)
            )
            Box(
                modifier = Modifier
                    .offset(y = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VerdeEtherealPerfil)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text("GOLD", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(nombre, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = TextoPrincipalPerfil)
    }
}

@Composable
fun SeccionMisPedidos() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Mis Pedidos", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextoPrincipalPerfil)
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.alienware),
                    contentDescription = "Imagen de mi pedido",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("PEDIDO #8821", fontSize = 12.sp, color = TextoSecundarioPerfil, fontWeight = FontWeight.Bold)
                    Text("Alienware 17 R4", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextoPrincipalPerfil)
                }
            }
        }
    }
}

@Composable
fun MenuOpciones(onIrAVender: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // <-- AQUI AGREGAMOS LA OPCION DE VENDER EN EL MENU
        ItemMenuEthereal(
            icono = Icons.Default.Add,
            titulo = "Vender un Artículo",
            subtitulo = "Publica tus propios productos",
            onClick = onIrAVender
        )
        ItemMenuEthereal(
            icono = Icons.Default.LocationOn,
            titulo = "Direcciones de Envío",
            subtitulo = "2 direcciones guardadas"
        )
        ItemMenuEthereal(
            icono = Icons.Default.ShoppingCart,
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
            icono = Icons.Default.Face,
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
    tieneNotificacion: Boolean = false,
    onClick: () -> Unit = {} // <-- Recibe el clic
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GrisFondoTarjeta),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // <-- Aplica el clic a la tarjeta
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
                Icon(imageVector = icono, contentDescription = null, tint = VerdeEtherealPerfil, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = titulo, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextoPrincipalPerfil)
                Text(text = subtitulo, fontSize = 12.sp, color = TextoSecundarioPerfil)
            }
            if (tieneNotificacion) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(RojoAlerta))
                Spacer(modifier = Modifier.width(8.dp))
            }
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Composable
fun BotonCerrarSesion(onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Salir", tint = Color.Red)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Cerrar Sesión", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPerfilPreview() {
    PantallaPerfil()
}