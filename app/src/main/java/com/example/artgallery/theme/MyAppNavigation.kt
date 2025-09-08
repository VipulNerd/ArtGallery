package com.example.artgallery

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artgallery.screens.DetailsScreen
import com.example.artgallery.theme.LogInPage
import com.example.artgallery.theme.Screen
import com.example.artgallery.theme.SignUpPage

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
        composable(Screen.Details.rout) {
            DetailsScreen(
                artId = "1",
                onBack = { navController.popBackStack() },
                onAddToCart = { navController.navigate(Screen.CartScreen.rout) }
            )
        }
        composable(Screen.CartScreen.rout) { CartScreenLayout(navController, cartViewModel = cartViewModel)}
    }
}