package com.example.artgallery.theme

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artgallery.ArtGalleryLayout

@Composable
fun MyAppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.rout, builder =  {
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
                navController
            )
        }
        composable(Screen.CartScreen.rout) { CartScreenLayout(navController)}
    })
}