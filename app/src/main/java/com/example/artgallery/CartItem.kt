package com.example.artgallery

data class CartItem(
    val id: String,
    val name: String,
    var quantity: Int,
    val price: Double,
    val imageResId: Int
)