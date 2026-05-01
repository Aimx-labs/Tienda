package com.example.mitienda.composables.Data

data class Articulo(
    val id: Long = System.currentTimeMillis(),
    val nombre: String,
    val precio: Float = 0.0f,
    val imagenUrl: String? = null,
    val talla: String? = null,
    val color: String? = null
) {
    // Si la API no manda imagen, usamos una de tecnología por defecto de Unsplash
    val urlSegura: String
        get() = imagenUrl ?: "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?q=80&w=500&auto=format&fit=crop"

    val tallaSegura: String
        get() = talla ?: "Única"

    val colorSeguro: String
        get() = color ?: "Estándar"
}