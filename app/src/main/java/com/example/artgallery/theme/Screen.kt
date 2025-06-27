package com.example.artgallery.theme

sealed class Screen (val rout : String){
    object Login : Screen("LogIn")
    object Signup : Screen("SignUp")
    object MainScreen: Screen("gallery")
    object CartScreen: Screen("Cart")

}