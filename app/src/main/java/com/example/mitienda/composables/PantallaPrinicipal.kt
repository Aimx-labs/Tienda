package com.example.mitienda.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FondoPrincipal = Color(0xFFF4F6F4)

@Composable
fun PantallaPrincipal(onProductoClick: () -> Unit = {}) { // <-- AQUÍ RECIBE EL CLIC
    Scaffold(
        containerColor = FondoPrincipal,
        topBar = { TopBarPrincipalEthereal() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            SeccionHero(modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(32.dp))
            // Pasamos el clic a las novedades
            SeccionNovedades(onProductoClick = onProductoClick)
            Spacer(modifier = Modifier.height(40.dp))
            SeccionColeccionesDestacadas(modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(48.dp))
            SeccionNewsletter(modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun TopBarPrincipalEthereal() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = VerdeEthereal, modifier = Modifier.size(24.dp))
        Text("The Aimox Store", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = VerdeEthereal)
        Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(Color.DarkGray))
    }
}

@Composable
fun SeccionHero(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = modifier.fillMaxWidth().height(420.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFF2C3E35))) {
            Column(modifier = Modifier.align(Alignment.BottomStart).padding(24.dp)) {
                Text("COLECCIÓN LIMITADA", color = Color.White.copy(alpha = 0.8f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Ofertas de\nVerano", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 36.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Acción explorar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeEthereal),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Text("Explorar Colección", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun SeccionNovedades(onProductoClick: () -> Unit = {}) { // <-- RECIBE EL CLIC AQUÍ TAMBIÉN
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text("Novedades", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextoPrincipal)
                Text("Recién llegados a nuestra tienda.", fontSize = 12.sp, color = TextoSecundario)
            }
            Text("Ver todo", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = VerdeEthereal)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Se lo pasamos a cada tarjeta
            TarjetaProductoMini("Motorola G14", "$250.00", Color(0xFF6B6652), onClick = onProductoClick)
            TarjetaProductoMini("Dell G3", "$499.00", Color(0xFF1E2328), onClick = onProductoClick)
            TarjetaProductoMini("MSI GE63 Thin", "$699.00", Color(0xFF8C7A6B), onClick = onProductoClick)
        }
    }
}

@Composable
fun TarjetaProductoMini(nombre: String, precio: String, colorFondo: Color, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clickable { onClick() } // <-- ¡AQUÍ HACEMOS QUE LA TARJETA SEA CLICKEABLE!
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(16.dp)).background(colorFondo))
        Spacer(modifier = Modifier.height(12.dp))
        Text(nombre, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextoPrincipal, maxLines = 1)
        Text(precio, fontSize = 14.sp, color = VerdeEthereal, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SeccionColeccionesDestacadas(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text("Colecciones Destacadas", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextoPrincipal)
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            TarjetaColeccion("Colección\nAimox", "Selección personal de productos aimox.", "Descubrir", Color(0xFF5D6B58), 280.dp)
            TarjetaColeccion("Accesorios Para El Hogar", "ILUMINACIÓN", null, Color(0xFF1E2B2B), 180.dp)
        }
    }
}

@Composable
fun TarjetaColeccion(titulo: String, subtitulo: String, botonTexto: String?, colorFondo: Color, altura: androidx.compose.ui.unit.Dp) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth().height(altura),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(colorFondo)) {
            Column(modifier = Modifier.align(Alignment.BottomStart).padding(20.dp)) {
                Text(titulo, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                Text(subtitulo, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SeccionNewsletter(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Mimo en el\nDetalle", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = VerdeEthereal, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Su correo electrónico", color = Color.LightGray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeEthereal),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Suscribirse", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    PantallaPrincipal()
}