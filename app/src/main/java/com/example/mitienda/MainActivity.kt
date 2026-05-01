package com.example.mitienda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mitienda.composables.AppNavegacion
import com.example.mitienda.composables.ListaCompras
import com.example.mitienda.ui.theme.MiTiendaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiTiendaTheme {
                AppNavegacion()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Button( onClick = {}) {
        Icon(Icons.Filled.Favorite, contentDescription= "" )
        Text("Hola $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiTiendaTheme {
        Greeting("Jonathan")
    }
}