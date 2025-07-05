package com.example.artgallery

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel(){
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems : List<CartItem> = _cartItems

    fun addItem(item: CartItem){
        _cartItems.add(item)
    }

    fun removeItem(item: CartItem){
        _cartItems.remove(item)

    }
}