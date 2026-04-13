package com.example.mitienda.network

import com.example.mitienda.composables.Data.Articulo
import retrofit2.http.GET

interface ApiArticulos {
    @GET("productos")
    suspend fun obtenerArticulos(): List<Articulo>
}