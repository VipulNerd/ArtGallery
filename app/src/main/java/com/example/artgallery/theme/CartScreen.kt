package com.example.artgallery.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.artgallery.CartItem
import com.example.artgallery.CartViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.example.artgallery.BottomBarContent
import com.example.artgallery.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreenLayout(navController: NavController, cartViewModel: CartViewModel){
    val cartItems = cartViewModel.cartItems
    val currentTab = remember { mutableStateOf(BottomBarContent.HOME) }
    val navItems = listOf(
        NavigationItemContent(BottomBarContent.HOME, R.drawable.home_24px, "Home"),
        NavigationItemContent(BottomBarContent.CART, R.drawable.add_shopping_cart_24px, "Cart"),
        NavigationItemContent(BottomBarContent.LOGOUT, R.drawable.logout_24px, "Account")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cart")
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(Screen.MainScreen.rout) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                navController = navController,
                currentTab=currentTab.value,
                navigationItems = navItems,
                onTabPressed = {selectedTab ->
                    currentTab.value = selectedTab
                }
            )
        }

    ) {
        Surface (modifier = Modifier.padding(it)){
            Column {
                LazyColumn {
                    items(cartItems) {items->
                        ItemCard(item = items, onRemove = {cartViewModel.removeItem(items)})
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard(item: CartItem, onRemove: () -> Unit){
    Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(item.imageResId),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = item.name)
            Text(text = item.quantity.toString())
            Text(text = item.price.toString())
        }
        Button(onClick = { onRemove() }){
            Text(text = "Remove")
        }
    }
}
