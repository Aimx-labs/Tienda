package com.example.mitienda.composables.Data

data class Articulo(
    val id: Long = System.currentTimeMillis(),
    val nombre: String,
    val precio: Float = 0.0f,
    // Agregamos estos campos para el diseño, con valores por defecto
    val imagenUrl: String = "https://images.unsplash.com/photo-1512436991641-6745cdb1723f", // URL de prueba
    val talla: String = "M",
    val color: String = "Standard"
)
