package com.example.artgallery.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArtGalleryViewModel : ViewModel() {
    var currentImage = mutableIntStateOf(1)
        private set

    private val _favorites = MutableStateFlow<Set<Int>>(emptySet())
    val favorites: StateFlow<Set<Int>> = _favorites.asStateFlow()

    private val _cart = MutableStateFlow<List<Int>>(emptyList())
    val cart: StateFlow<List<Int>> = _cart.asStateFlow()

    fun nextImage() {
        if (currentImage.intValue < 3) {
            currentImage.intValue += 1
        }
    }

    fun prevImage() {
        if (currentImage.intValue > 1) {
            currentImage.intValue -= 1
        }
    }

    fun toggleFavorite(imageId: Int) {
        val current = _favorites.value.toMutableSet()
        if (current.contains(imageId)) {
            current.remove(imageId)
        } else {
            current.add(imageId)
        }
        _favorites.value = current
    }

    fun addToCart(imageId: Int) {
        val updated = _cart.value.toMutableList()
        updated.add(imageId)
        _cart.value = updated
    }

    fun removeFromCart(imageId: Int) {
        val updated = _cart.value.toMutableList()
        updated.remove(imageId)
        _cart.value = updated
    }
}