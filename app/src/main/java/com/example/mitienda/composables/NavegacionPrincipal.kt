package com.example.mitienda.composables

import androidx.navigation.NavType
import androidx.navigation.navArgument
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    var usuarioActual by remember { mutableStateOf("Invitado") }
    Scaffold(
        bottomBar = {
            // Se agregó "vender" para que también oculte la barra abajo
            if (rutaActual != "login" && rutaActual != "registro" && rutaActual != "vender") {
                BarraNavegacionReal(navController, rutaActual)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = if (rutaActual == "login" || rutaActual == "registro" || rutaActual == "vender") Modifier else Modifier.padding(paddingValues)
        ) {

            composable("login") {
                PantallaLogin(
                    onLoginSuccess = { nombreIngresado ->
                        usuarioActual = nombreIngresado
                        navController.navigate("principal") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onIrARegistro = { navController.navigate("registro") }
                )
            }

            composable("registro") {
                PantallaRegistro(
                    onRegistroExitoso = { navController.popBackStack() },
                    onIrALogin = { navController.popBackStack() }
                )
            }

            composable("principal") {
                PantallaPrincipal(
                    onProductoClick = { navController.navigate("detalles_producto") },
                    onPerfilClick = { navController.navigate("perfil") },
                    onIrAVender = { navController.navigate("vender") } // <-- Acción conectada
                )
            }

            composable("categorias") {
                PantallaCategorias(
                    onProductoClick = { navController.navigate("detalles_producto") },
                    onPerfilClick = { navController.navigate("perfil") }
                )
            }

            composable("carrito") {
                ListaCompras(
                    onIrAPagar = { totalCalculado ->
                        navController.navigate("confirmacion_pago/$totalCalculado")
                    },
                    onPerfilClick = { navController.navigate("perfil") }
                )
            }

            composable("detalles_producto") {
                PantallaDetallesProducto(
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "confirmacion_pago/{subtotal}",
                arguments = listOf(navArgument("subtotal") { type = NavType.FloatType })
            ) { backStackEntry ->
                val subtotalExtraido = backStackEntry.arguments?.getFloat("subtotal")?.toDouble() ?: 0.0

                PantallaConfirmacionPago(
                    subtotal = subtotalExtraido,
                    onNavigateHome = {
                        navController.navigate("principal") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    }
                )
            }

            // Perfil duplicado eliminado. Este es el correcto:
            composable("perfil") {
                PantallaPerfil(
                    nombreUsuario = usuarioActual,
                    imagenPerfil = com.example.mitienda.R.drawable.amongus,
                    onCerrarSesion = {
                        usuarioActual = "Invitado"
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onIrAVender = { navController.navigate("vender") } // <-- Acción conectada
                )
            }

            // <-- NUEVA RUTA: LA PANTALLA DE VENDER
            composable("vender") {
                PantallaVender(
                    onPublicacionExitosa = {
                        navController.navigate("principal") {
                            popUpTo("vender") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BarraNavegacionReal(navController: NavHostController, rutaActual: String?) {
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