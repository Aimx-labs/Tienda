package com.example.mitienda.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mitienda.R
import com.example.mitienda.ui.theme.MiTiendaTheme

// Colores
val ColorBoy = Color(0xFF8BC34A)
val ColorGirl = Color(0xFFF48FB1)
val ColorFondo = Color(0xFFFFFDF7)
val ColorBotonAzul = Color(0xFF4FC3F7)

@Composable
fun PantallaCrearPerfil() {
    //Estados
    var generoSeleccionado by remember { mutableStateOf("Boy") }
    var edadSeleccionada by remember { mutableIntStateOf(5) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Encabezado
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Atrás",
                tint = ColorBotonAzul,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Crear Perfil",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        //Genero
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BotonGenero(
                label = "Niño",
                color = ColorBoy,
                isSelected = generoSeleccionado == "Boy",
                modifier = Modifier.weight(1f),
                onClick = { generoSeleccionado = "Boy" }
            )
            BotonGenero(
                label = "Niña",
                color = ColorGirl,
                isSelected = generoSeleccionado == "Girl",
                modifier = Modifier.weight(1f),
                onClick = { generoSeleccionado = "Girl" }
            )
        }
        // Ilustración Central
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.astral),
                contentDescription = "Avatar",
                modifier = Modifier.size(280.dp)
            )
        }
        //Edad
        Text(
            text = "Selecciona Tu Edad",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        //Cambio De Estado
        SelectorEdadHorizontal(
            edadSeleccionada = edadSeleccionada,
            onEdadSelected = { nuevaEdad -> edadSeleccionada = nuevaEdad }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Guardar
        Button(
            onClick = {
                println("Guardando: Perfil de $generoSeleccionado con $edadSeleccionada años")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ColorBotonAzul),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text(text = "Guardar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BotonGenero(
    label: String,
    color: Color,
    isSelected: Boolean,
    modifier: Modifier,
    onClick: () -> Unit //Funcionamiento Del Click
) {
    // Usamos el alfa para que el botón no seleccionado se vea más claro
    val backgroundOpacity = if (isSelected) 1f else 0.4f

    Surface(
        color = color.copy(alpha = backgroundOpacity),
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .height(50.dp)
            .clickable { onClick() } // Detecta el toque
    ) {
        Box(contentAlignment = Alignment.Center) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Face, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = label, color = Color.White, fontWeight = FontWeight.Bold)
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                        .size(18.dp)
                )
            }
        }
    }
}

@Composable
fun SelectorEdadHorizontal(edadSeleccionada: Int, onEdadSelected: (Int) -> Unit) {
    val edades = (1..12).toList()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(edades) { edad ->
            val esTarget = edad == edadSeleccionada
            Text(
                text = edad.toString(),
                fontSize = if (esTarget) 48.sp else 28.sp,
                fontWeight = FontWeight.Black,
                color = if (esTarget) Color.DarkGray else Color.LightGray.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onEdadSelected(edad) } // Al presionar una opcion se resalta
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaCrearPerfilPreview() {
    MiTiendaTheme {
        PantallaCrearPerfil()
    }
}

@Preview(showBackground = true)
@Composable
fun BotonGeneroPreview() {
    MiTiendaTheme {
        BotonGenero(
            label = "Boy",
            color = ColorBoy,
            isSelected = true,
            modifier = Modifier.width(150.dp),
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SelectorEdadHorizontalPreview() {
    MiTiendaTheme {
        SelectorEdadHorizontal(
            edadSeleccionada = 5,
            onEdadSelected = {}
        )
    }
}