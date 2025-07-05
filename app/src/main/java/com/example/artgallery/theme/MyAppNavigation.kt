package com.example.artgallery.theme

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artgallery.ArtGalleryLayout
import com.example.artgallery.CartViewModel

@Composable
fun MyAppNavigation(){
    val navController = rememberNavController()
    val cartViewModel = viewModel<CartViewModel>()

    NavHost(navController = navController, startDestination = Screen.Login.rout) {
        composable(Screen.Login.rout){
            LogInPage(
                navController
            )
        }
        composable(Screen.Signup.rout) {
            SignUpPage(
                navController
            )
        }
        composable(Screen.MainScreen.rout){
            ArtGalleryLayout(
                navController, cartViewModel = cartViewModel
            )
        }
        composable(Screen.CartScreen.rout) { CartScreenLayout(navController, cartViewModel = cartViewModel)}
    }
}