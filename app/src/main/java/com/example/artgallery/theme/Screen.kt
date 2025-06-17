package com.example.artgallery.theme

sealed class Screen (val rout : String){
    object Login : Screen("login")
    object Signup : Screen("signup")
    object MainScreen: Screen("gallery")

}