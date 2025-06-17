package com.example.artgallery.theme

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

@Composable
fun MyAppNavigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.rout) {
        composable(Screen.Login.rout){
            LogInPage(navController,authViewModel)
        }
        composable(Screen.Signup.rout) {
            SignUpPage(
                navController,
                authViewModel
            )
        }
        composable(Screen.MainScreen.rout){
            ArtGalleryLayout(
                navController,
                authViewModel
            )
        }
    }
}