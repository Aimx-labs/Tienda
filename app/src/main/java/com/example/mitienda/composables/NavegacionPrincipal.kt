package com.example.mitienda.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    // Simulamos un estado de sesión (luego lo puedes pasar a un ViewModel)
    var usuarioLogueado by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = if (usuarioLogueado) "principal" else "login"
    ) {
        composable("login") {
            // Usa la PantallaLogin que te pasé en el mensaje anterior
            PantallaLogin(onLoginSuccess = { usuarioLogueado = true })
        }

        composable("principal") {
            Scaffold(bottomBar = { BarraNavegacionReal(navController) }) { p ->
                Box(modifier = Modifier.padding(p)) {
                    PantallaPrincipal(onProductoClick = { navController.navigate("detalles_producto") })
                }
            }
        }

        composable("categorias") {
            Scaffold(bottomBar = { BarraNavegacionReal(navController) }) { p ->
                Box(modifier = Modifier.padding(p)) { PantallaCategorias() }
            }
        }

        composable("carrito") {
            Scaffold(bottomBar = { BarraNavegacionReal(navController) }) { p ->
                Box(modifier = Modifier.padding(p)) {
                    ListaCompras(onIrAPagar = { navController.navigate("confirmacion_pago") })
                }
            }
        }

        composable("perfil") {
            Scaffold(bottomBar = { BarraNavegacionReal(navController) }) { p ->
                Box(modifier = Modifier.padding(p)) {
                    PantallaPerfil()
                    // Nota: Dentro de PantallaPerfil, el botón de vender
                    // debe llamar a navController.navigate("vender")
                }
            }
        }

        composable("vender") {
            PantallaPublicar(onProductoPublicado = { navController.popBackStack() })
        }

        composable("detalles_producto") {
            PantallaDetallesProducto(onBack = { navController.popBackStack() })
        }

        composable("confirmacion_pago") {
            PantallaConfirmacionPago(onNavigateHome = { navController.navigate("principal") })
        }
    }
}
@Composable
fun BarraNavegacionReal(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val VerdeOscuro = Color(0xFF135041)
    val FondoCantidad = Color(0xFFE8F0ED)

    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Principal") },
            selected = rutaActual == "principal",
            onClick = { navController.navigate("principal") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = VerdeOscuro, indicatorColor = FondoCantidad)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, null) },
            label = { Text("Categorias") },
            selected = rutaActual == "categorias",
            onClick = { navController.navigate("categorias") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = VerdeOscuro, indicatorColor = FondoCantidad)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, null) },
            label = { Text("Carrito") },
            selected = rutaActual == "carrito",
            onClick = { navController.navigate("carrito") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color.White, indicatorColor = VerdeOscuro)
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, null) },
            label = { Text("Perfil") },
            selected = rutaActual == "perfil",
            onClick = { navController.navigate("perfil") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = VerdeOscuro, indicatorColor = FondoCantidad)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavegacionPreview() {
    AppNavegacion()
}