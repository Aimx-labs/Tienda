package com.example.mitienda.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProductos {
    val BASE_URL="https://fast-api-v1jp.onrender.com/"

    val apiArticulosService:ApiArticulos by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiArticulos::class.java)
    }

}