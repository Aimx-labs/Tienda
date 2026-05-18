package com.example.mitienda.composables.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class ItemCarrito(
    val articulo: Articulo,
    val cantidad: Int
)

object CartManager {
    // Lista reactiva global de elementos en el carrito
    val listaCarrito = mutableStateListOf<ItemCarrito>()

    // Guarda temporalmente el artículo que el usuario seleccionó para ver los detalles
    var articuloSeleccionado by mutableStateOf<Articulo?>(null)

    fun agregarProducto(articulo: Articulo) {
        val index = listaCarrito.indexOfFirst { it.articulo.id == articulo.id }
        if (index != -1) {
            val itemActual = listaCarrito[index]
            listaCarrito[index] = itemActual.copy(cantidad = itemActual.cantidad + 1)
        } else {
            listaCarrito.add(ItemCarrito(articulo, 1))
        }
    }

    fun decrementarProducto(articulo: Articulo) {
        val index = listaCarrito.indexOfFirst { it.articulo.id == articulo.id }
        if (index != -1) {
            val itemActual = listaCarrito[index]
            if (itemActual.cantidad > 1) {
                listaCarrito[index] = itemActual.copy(cantidad = itemActual.cantidad - 1)
            } else {
                listaCarrito.removeAt(index)
            }
        }
    }

    fun eliminarProducto(articulo: Articulo) {
        listaCarrito.removeAll { it.articulo.id == articulo.id }
    }
}