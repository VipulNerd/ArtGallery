package com.example.artgallery

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels


import com.example.artgallery.theme.AuthViewModel
import com.example.artgallery.theme.MyAppNavigation
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels()
        setContent {
            ArtGalleryTheme {

                MyAppNavigation(
                    authViewModel = authViewModel
                )

            }
        }
    }
}

