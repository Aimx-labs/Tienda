package com.example.mitienda.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaRegistro(
    onRegistroExitoso: () -> Unit = {},
    onIrALogin: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFF4F6F4)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Crea tu cuenta en", fontSize = 16.sp, color = Color.Gray)
            Text("The Aimox Store", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF135041))

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = nombre, onValueChange = { nombre = it; mensajeError = "" },
                label = { Text("Nombre completo") },
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email, onValueChange = { email = it; mensajeError = "" },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password, onValueChange = { password = it; mensajeError = "" },
                label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmPassword, onValueChange = { confirmPassword = it; mensajeError = "" },
                label = { Text("Confirmar Contraseña") }, visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), singleLine = true
            )

            if (mensajeError.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = mensajeError, color = Color.Red, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (nombre.isBlank() || email.isBlank() || password.isBlank()) {
                        mensajeError = "Por favor, llena todos los campos"
                    } else if (password != confirmPassword) {
                        mensajeError = "Las contraseñas no coinciden"
                    } else if (BaseDatosUsuarios.listaUsuarios.any { it.correo == email }) {
                        mensajeError = "Este correo ya está registrado"
                    } else {
                        // Guardamos al nuevo usuario en la base de datos simulada
                        BaseDatosUsuarios.registrar(UsuarioApp(email, password, nombre))
                        onRegistroExitoso()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF135041)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Registrarse", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Row {
                Text("¿Ya tienes cuenta? ", color = Color.Gray)
                Text(
                    "Inicia Sesión",
                    color = Color(0xFF135041),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onIrALogin() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaRegistroPreview() {
    PantallaRegistro()
}