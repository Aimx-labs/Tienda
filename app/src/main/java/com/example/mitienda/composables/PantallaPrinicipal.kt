package com.example.mitienda.composables

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.mitienda.R
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val FondoPrincipal = Color(0xFFF4F6F4)

@Composable
fun PantallaPrincipal(
    onProductoClick: () -> Unit = {},
    onPerfilClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = FondoPrincipal,
        topBar = { TopBarPrincipalEthereal(onPerfilClick = onPerfilClick) }
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
fun TopBarPrincipalEthereal(onPerfilClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = VerdeEthereal, modifier = Modifier.size(24.dp))
        Text("The Aimox Store", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = VerdeEthereal)

        Image(
            painter = painterResource(id = R.drawable.amongus),
            contentDescription = "Avatar de Usuario",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .clickable { onPerfilClick() }
        )
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
            // IMAGEN DE FONDO ONLINE
            AsyncImage(
                model = "https://images.unsplash.com/photo-1593640408182-31c70c8268f5?q=80&w=1000&auto=format&fit=crop",
                contentDescription = "Fondo Hero",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Degradado para que el texto sea legible
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)))

            Column(modifier = Modifier.align(Alignment.BottomStart).padding(24.dp)) {
                Text("OFERTAS EXCLUSIVAS", color = Color.White.copy(alpha = 0.8f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Potencia sin\nLímites", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 36.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Acción explorar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeEthereal),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Text("Ver Laptops", fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun SeccionNovedades(onProductoClick: () -> Unit = {}) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text("Novedades", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextoPrincipal)
                Text("Lo último en tecnología Aimox.", fontSize = 12.sp, color = TextoSecundario)
            }
            Text("Ver todo", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = VerdeEthereal)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TarjetaProductoMini("Razer DEATHADDER", "$35.00", "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcReGt6Wj9lJncAz3VaQ6_d-fxRNjODMsxXe3yw6QsiH9xOeh1iL5I7xAk7ZmC6Lf3Ty7DXBOh_XzdxokS0IWnm3KP2dUHdQ4wxGV8C0gij7i5vcy0_k5fadm_a8GbUG1rMMUAAUfw&usqp=CAc", onClick = onProductoClick)
            TarjetaProductoMini("Teclado Mecánico", "$75.00", "https://images.unsplash.com/photo-1595225476474-87563907a212?q=80&w=500&auto=format&fit=crop", onClick = onProductoClick)
            TarjetaProductoMini("MSI GF63 Thin", "$699.00", "https://storage-asset.msi.com/global/picture/product/product_1689905089a261a8d64d2b0b391aaadaa03de3850f.webp", onClick = onProductoClick)
        }
    }
}

@Composable
fun TarjetaProductoMini(nombre: String, precio: String, imageUrl: String, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.width(160.dp).clickable { onClick() }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(16.dp)).background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(nombre, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextoPrincipal, maxLines = 1)
        Text(precio, fontSize = 14.sp, color = VerdeEthereal, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SeccionColeccionesDestacadas(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text("Categorías Destacadas", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextoPrincipal)
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            TarjetaColeccion("Setup Gamer", "Todo para armar tu setup ideal.", "Descubrir", "https://images.unsplash.com/photo-1603302576837-37561b2e2302?q=80&w=500&auto=format&fit=crop", 280.dp)
            TarjetaColeccion("Componentes", "GPUS,CPUS,RAM,SSD,HDD Entre otros", null, "https://images.unsplash.com/photo-1518770660439-4636190af475?q=80&w=500&auto=format&fit=crop", 180.dp)
        }
    }
}

@Composable
fun TarjetaColeccion(titulo: String, subtitulo: String, botonTexto: String?, imageUrl: String, altura: androidx.compose.ui.unit.Dp) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth().height(altura),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            AsyncImage(
                model = imageUrl,
                contentDescription = titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Sombra para leer el texto
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)))

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
            Text(
                text = "Suscribirse",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // ¡Aquí está la magia!
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalPreview() {
    PantallaPrincipal()
}