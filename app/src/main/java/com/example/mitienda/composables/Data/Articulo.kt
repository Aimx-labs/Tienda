package com.example.mitienda.composables.Data

data class Articulo(
    val id: Long = System.currentTimeMillis(),
    val nombre: String,
    val precio: Float = 0.0f
)
