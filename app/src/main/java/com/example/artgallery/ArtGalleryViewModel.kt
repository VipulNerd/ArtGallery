package com.example.artgallery.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class ArtGalleryViewModel : ViewModel() {
    var currentImage = mutableIntStateOf(1)
        private set

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
}