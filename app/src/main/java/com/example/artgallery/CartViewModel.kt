package com.example.artgallery

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    fun addItem(item: CartItem) {
        val existingItem = _cartItems.find { it.id == item.id }
        if (existingItem != null) {
            existingItem.quantity += item.quantity
        } else {
            _cartItems.add(item)
        }
    }

    fun removeItem(item: CartItem) {
        _cartItems.remove(item)
    }

    fun getTotalPrice(): Double {
        return _cartItems.sumOf { it.price * it.quantity }
    }

    fun clearCart() {
        _cartItems.clear()
    }
}